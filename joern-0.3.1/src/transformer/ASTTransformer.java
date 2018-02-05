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

public class ASTTransformer extends ASTNodeVisitor {

	private Stack<ASTNode> parentStack;
	private Stack<Integer> indexStack;

	public void transform(ASTNode node) {
		System.out.println("transform!!!");
		parentStack = new Stack<ASTNode>();
		indexStack = new Stack<Integer>();
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
		defaultHandler(expression);
	}

	@Override
	public void visit(WhileStatement expression)
	{
		// TODO: transform randomly
		ASTNode parent = parentStack.peek();
		Integer index = indexStack.peek();
		ForStatement forStatement = new ForStatement();
		forStatement.setStatement(expression.getStatement()); 
		forStatement.setCondition(expression.getCondition());
		parent.setChild(index, forStatement);
		defaultHandler(forStatement);
	}

	@Override
	public void visit(DoStatement expression)
	{
		defaultHandler(expression);
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

	public void visitChildren(ASTNode item)
	{
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
	}

}