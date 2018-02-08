package transformer;

import java.util.Stack;

import ast.ASTNode;
import ast.declarations.ClassDefStatement;
import ast.expressions.Argument;
import ast.expressions.AssignmentExpr;
import ast.expressions.CallExpression;
import ast.expressions.Identifier;
import ast.expressions.MemberAccess;
import ast.expressions.PrimaryExpression;
import ast.expressions.UnaryExpression;
import ast.functionDef.FunctionDef;
import ast.functionDef.Parameter;
import ast.functionDef.ParameterList;
import ast.statements.BreakStatement;
import ast.statements.CompoundStatement;
import ast.statements.Condition;
import ast.statements.ContinueStatement;
import ast.statements.DoStatement;
import ast.statements.ExpressionStatement;
import ast.statements.ForStatement;
import ast.statements.GotoStatement;
import ast.statements.IdentifierDeclStatement;
import ast.statements.IfStatement;
import ast.statements.Label;
import ast.statements.ReturnStatement;
import ast.statements.SwitchStatement;
import ast.statements.WhileStatement;
import databaseNodes.FunctionDatabaseNode;
import ast.walking.ASTNodeVisitor;
import java.util.Map;
import java.util.HashMap;

import java.util.Random;

public class ASTTransformer extends ASTNodeVisitor {

	private Stack<ASTNode> parentStack;
	private Stack<Integer> indexStack;
	private int maxDepth = 10;
	private int curDepth = 0;

	public void transform(ASTNode node) {
		parentStack = new Stack<ASTNode>();
		indexStack = new Stack<Integer>();
		curDepth = 0;
		node.accept(this);
	}

	@Override
	public void visit(ParameterList item)
	{
		defaultHandler(item);
	}

	@Override
	public void visit(Parameter item)
	{
		defaultHandler(item);
	}
	
	@Override
	public void visit(FunctionDef item)
	{
		defaultHandler(item);
	}

	@Override
	public void visit(ClassDefStatement item)
	{
		defaultHandler(item);
	}

	@Override
	public void visit(IdentifierDeclStatement statementItem)
	{
		defaultHandler(statementItem);
	}

	@Override
	public void visit(ExpressionStatement statementItem)
	{
		defaultHandler(statementItem);
	}

	@Override
	public void visit(CallExpression expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(Condition expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(AssignmentExpr expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(PrimaryExpression expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(Identifier expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(MemberAccess expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(UnaryExpression expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(Argument expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(ReturnStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(GotoStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(ContinueStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(BreakStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(CompoundStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(IfStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(ForStatement expression)
	{
		// all of these transformation don't consider break and continue expressions;
		// semantic results are provided by code test case
		
		defaultHandler(expression);
		Random rand = new Random();
		int  n = rand.nextInt(2);
		ASTNode parent = parentStack.peek();
		Integer index = indexStack.peek();
		switch (n) {
			case 0 : {
				// WHILE 
				/*
					for (init;cond;expr) {
						statements;
					}
					----------------------
					init;
					while (cond) {
						statements;
						expr;
					}
				*/
				CompoundStatement compoundStatement = new CompoundStatement();
				WhileStatement whileStatement = new WhileStatement();
				whileStatement.setCondition(expression.getCondition());
				CompoundStatement inCompoundStatement = new CompoundStatement();
				inCompoundStatement.addChild(expression.getStatement());
				ExpressionStatement expressionStatement = new ExpressionStatement();
				expressionStatement.setCodeStr(expression.getExpression().getEscapedCodeStr()+";");
				inCompoundStatement.addChild(expressionStatement);
				whileStatement.setStatement(inCompoundStatement);
				compoundStatement.addChild(expression.getForInitStatement());
				compoundStatement.addChild(whileStatement);
				parent.setChild(index, compoundStatement);
				break;
			}
			case 1 : {
				// GOTO
				/*
					init;
					loop:
						if (cond) {
							statements;
							expr;
							goto: loop
						}
				*/
				CompoundStatement compoundStatement = new CompoundStatement();
				compoundStatement.addChild(expression.getForInitStatement());
				Label label = new Label();
				label.setCodeStr("loop:");
				compoundStatement.addChild(label);
				IfStatement ifStatement = new IfStatement();
				ifStatement.setCondition(expression.getCondition());
				CompoundStatement inCompoundStatement = new CompoundStatement();
				inCompoundStatement.addChild(expression.getStatement());
				ExpressionStatement expressionStatement = new ExpressionStatement();
				expressionStatement.setCodeStr(expression.getExpression().getEscapedCodeStr()+";");
				inCompoundStatement.addChild(expressionStatement);
				GotoStatement gotoStatement = new GotoStatement();
				gotoStatement.addChild(label);
				inCompoundStatement.addChild(gotoStatement);
				ifStatement.setStatement(inCompoundStatement);
				compoundStatement.addChild(ifStatement);
				parent.setChild(index, compoundStatement);
				break;
			}
		}


	}

	@Override
	public void visit(WhileStatement expression)
	{
		defaultHandler(expression);
		// TODO: transform randomly
		ASTNode parent = parentStack.peek();
		Integer index = indexStack.peek();
		ForStatement forStatement = new ForStatement();
		forStatement.setStatement(expression.getStatement()); 
		forStatement.setCondition(expression.getCondition());
		parent.setChild(index, forStatement);
	}

	@Override
	public void visit(DoStatement expression)
	{
		defaultHandler(expression);
		ASTNode parent = parentStack.peek();
		Integer index = indexStack.peek();
		Random rand = new Random();
		int  n = rand.nextInt(2);
		if (n == 0) {
			IfStatement ifStatement = new IfStatement();
			Condition condition = new Condition();
			condition.setCodeStr("true");
			ifStatement.setCondition(condition);
			CompoundStatement compoundStatement = new CompoundStatement();
			WhileStatement whileStatement = new WhileStatement();
			whileStatement.setCondition(expression.getCondition());
			whileStatement.setStatement(expression.getStatement());
			compoundStatement.addChild(expression.getStatement());
			compoundStatement.addChild(whileStatement);
			ifStatement.addChild(compoundStatement);
			parent.setChild(index, ifStatement);
		} else if (n == 1) {
			DoStatement doStatement = new DoStatement();
			Condition condition = new Condition();
			condition.setCodeStr("false");
			doStatement.setCondition(condition);
			doStatement.setStatement(expression.getStatement());
			WhileStatement whileStatement = new WhileStatement();
			whileStatement.setCondition(expression.getCondition());
			whileStatement.setStatement(expression.getStatement());
			CompoundStatement compoundStatement = new CompoundStatement();
			compoundStatement.addChild(doStatement);
			compoundStatement.addChild(whileStatement);
			parent.setChild(index, compoundStatement);
		}
	}

	@Override
	public void visit(Label expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visit(SwitchStatement expression)
	{
		defaultHandler(expression);
	}

	@Override
	public void visitChildren(ASTNode item)
	{
		curDepth++;
		if (curDepth > maxDepth) {
			return;
		}
		parentStack.push(item);
		int nChildren = item.getChildCount();

		for (int i = 0; i < nChildren; i++)
		{
			indexStack.push(i);
			ASTNode child = item.getChild(i);
			child.accept(this);
			indexStack.pop();
		}
		parentStack.pop();
		curDepth--;
	}

}