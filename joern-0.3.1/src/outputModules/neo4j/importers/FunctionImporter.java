package outputModules.neo4j.importers;

import neo4j.batchInserter.Neo4JBatchInserter;

import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.RelationshipType;

import ast.ASTNode;
import ast.functionDef.FunctionDef;
import ast.statements.CompoundStatement;
import cfg.CFG;
import cfg.nodes.ASTNodeContainer;
import cfg.nodes.CFGNode;
import databaseNodes.EdgeTypes;
import databaseNodes.FileDatabaseNode;
import databaseNodes.FunctionDatabaseNode;

import encoder.ASTEncoder;
import encoder.CFGEncoder;
import encoder.CDGEncoder;

import printer.ASTPrinter;
import transformer.ASTTransformer;

// Stays alive while importing a function into
// the database

public class FunctionImporter extends ASTNodeImporter
{
	ASTImporter astImporter = new ASTImporter(nodeStore);
	CFGImporter cfgImporter = new CFGImporter(nodeStore);
	UDGImporter udgImporter = new UDGImporter(nodeStore);
	DDGImporter ddgImporter = new DDGImporter(nodeStore);
	CDGImporter cdgImporter = new CDGImporter(nodeStore);

	public void addToDatabaseSafe(ASTNode node)
	{
		try
		{
			FunctionDatabaseNode function = new FunctionDatabaseNode();
			// this actually constructs all other representations of
			// the function.
			function.initialize(node);
			addFunctionToDatabase(function);
			linkFunctionToFileNode(function, curFile);

			// transform
			ASTTransformer astTransformer = new ASTTransformer();
			astTransformer.transform(function.getASTRoot());
			astTransformer.transform(function.getASTRoot());
			// print
			ASTPrinter astPrinter = new ASTPrinter();
			astPrinter.print(function.getASTRoot());
			// encode
			// ASTEncoder astEncoder = new ASTEncoder();
			// CFGEncoder cfgEncoder = new CFGEncoder();
			// CDGEncoder cdgEncoder = new CDGEncoder();
			// System.out.println(astEncoder.encodeToString(function.getASTRoot()));
			// System.out.println(cfgEncoder.encodeToString(function.getCFG()));
			// System.out.println(cdgEncoder.encodeToString(function.getCDG()));
		}
		catch (RuntimeException ex)
		{
			// ex.printStackTrace();
			System.err.println("Error adding function to database: "
					+ ((FunctionDef) node).name.getEscapedCodeStr());
			return;
		}
	}

	private void addFunctionToDatabase(FunctionDatabaseNode function)
	{

		addMainNode(function);
		
		astImporter.setCurrentFunction(function);
		cfgImporter.setCurrentFunction(function);
		udgImporter.setCurrentFunction(function);

		astImporter.addASTToDatabase(function.getASTRoot());
		cfgImporter.addCFGToDatabase(function.getCFG());
		udgImporter.addUDGToDatabase(function.getUDG());
		ddgImporter.addDDGToDatabase(function.getDDG());
		cdgImporter.addCDGToDatabase(function.getCDG());

		linkFunctionToASTAndCFG(function);

	}

	private void linkFunctionToASTAndCFG(FunctionDatabaseNode function)
	{

		linkFunctionWithAST(function);

		CFG cfg = function.getCFG();
		if (cfg != null)
		{
			linkFunctionWithCFG(function, cfg);
		}
	}

	private void linkFunctionWithAST(FunctionDatabaseNode function)
	{
		RelationshipType rel = DynamicRelationshipType
				.withName(EdgeTypes.IS_FUNCTION_OF_AST);

		long functionId = nodeStore.getIdForObject(function);
		long astNodeId = nodeStore.getIdForObject(function.getASTRoot());

		Neo4JBatchInserter.addRelationship(functionId, astNodeId, rel, null);

	}

	private void linkFunctionWithCFG(FunctionDatabaseNode function, CFG cfg)
	{
		RelationshipType rel = DynamicRelationshipType
				.withName(EdgeTypes.IS_FUNCTION_OF_CFG);
		long functionId = nodeStore.getIdForObject(function);

		CFGNode firstBlock = cfg.getEntryNode();

		long cfgRootId;
		try
		{
			cfgRootId = nodeStore.getIdForObject(firstBlock);
		}
		catch (RuntimeException ex)
		{
			cfgRootId = nodeStore
					.getIdForObject(((ASTNodeContainer) firstBlock)
							.getASTNode());
		}

		Neo4JBatchInserter.addRelationship(functionId, cfgRootId, rel, null);

	}

	private void linkFunctionToFileNode(FunctionDatabaseNode function,
			FileDatabaseNode fileNode)
	{
		RelationshipType rel = DynamicRelationshipType
				.withName(EdgeTypes.IS_FILE_OF);

		long fileId = fileNode.getId();
		long functionId = nodeStore.getIdForObject(function);

		Neo4JBatchInserter.addRelationship(fileId, functionId, rel, null);
	}

}
