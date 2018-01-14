package encoder;

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

public class ASTEncoder extends ASTNodeVisitor {
	public Integer astParameterListCount = 0;
	public Integer astParameterCount = 0;
	public Integer astFunctionDefCount = 0;
	public Integer astClassDefStatementCount = 0;
	public Integer astIdentifierDeclStatementCount = 0;
	public Integer astExpressionStatementCount = 0;
	public Integer astCallExpressionCount = 0;
	public Integer astConditionCount = 0;
	public Integer astAssignmentExprCount = 0;
	public Integer astPrimaryExpressionCount = 0;
	public Integer astIdentifierCount = 0;
	public Integer astMemberAccessCount = 0;
	public Integer astUnaryExpressionCount = 0;
	public Integer astArgumentCount = 0;
	public Integer astReturnStatementCount = 0;
	public Integer astGotoStatementCount = 0;
	public Integer astContinueStatementCount = 0;
	public Integer astBreakStatementCount = 0;
	public Integer astCompoundStatementCount = 0;
	public Integer astIfStatementCount = 0;
	public Integer astForStatementCount = 0;
	public Integer astWhileStatementCount = 0;
	public Integer astDoStatementCount = 0;
	public Integer astLabelCount = 0;
	public Integer astSwitchStatementCount = 0;
	
	public String encode(ASTNode node) {
		FunctionDatabaseNode function = new FunctionDatabaseNode();
		function.initialize(node);
		FunctionDef astRoot = (FunctionDef) function.getASTRoot();
		visit(astRoot);
		return prettyString();
	}

	private String prettyString() {
		StringBuilder s = new StringBuilder();
		s.append("<ASTEncoder>\n");
		s.append("  astParameterListCount = " + astParameterListCount.toString() + '\n');
		s.append("  astParameterCount = " + astParameterCount.toString() + '\n');
		s.append("  astFunctionDefCount = " + astFunctionDefCount.toString() + '\n');
		s.append("  astClassDefStatementCount = " + astClassDefStatementCount.toString() + '\n');
		s.append("  astIdentifierDeclStatementCount = " + astIdentifierDeclStatementCount.toString() + '\n');
		s.append("  astExpressionStatementCount = " + astExpressionStatementCount.toString() + '\n');
		s.append("  astCallExpressionCount = " + astCallExpressionCount.toString() + '\n');
		s.append("  astConditionCount = " + astConditionCount.toString() + '\n');
		s.append("  astAssignmentExprCount = " + astAssignmentExprCount.toString() + '\n');
		s.append("  astPrimaryExpressionCount = " + astPrimaryExpressionCount.toString() + '\n');
		s.append("  astIdentifierCount = " + astIdentifierCount.toString() + '\n');
		s.append("  astMemberAccessCount = " + astMemberAccessCount.toString() + '\n');
		s.append("  astUnaryExpressionCount = " + astUnaryExpressionCount.toString() + '\n');
		s.append("  astArgumentCount = " + astArgumentCount.toString() + '\n');
		s.append("  astReturnStatementCount = " + astReturnStatementCount.toString() + '\n');
		s.append("  astGotoStatementCount = " + astGotoStatementCount.toString() + '\n');
		s.append("  astContinueStatementCount = " + astContinueStatementCount.toString() + '\n');
		s.append("  astBreakStatementCount = " + astBreakStatementCount.toString() + '\n');
		s.append("  astCompoundStatementCount = " + astCompoundStatementCount.toString() + '\n');
		s.append("  astIfStatementCount = " + astIfStatementCount.toString() + '\n');
		s.append("  astForStatementCount = " + astForStatementCount.toString() + '\n');
		s.append("  astWhileStatementCount = " + astWhileStatementCount.toString() + '\n');
		s.append("  astDoStatementCount = " + astDoStatementCount.toString() + '\n');
		s.append("  astLabelCount = " + astLabelCount.toString() + '\n');
		s.append("  astSwitchStatementCount = " + astSwitchStatementCount.toString() + '\n');
		s.append("</ASTEncoder>\n");
		return s.toString();
	}

	@Override
	public void visit(ParameterList item)
	{
		astParameterListCount++;
		defaultHandler(item);
	}

	@Override
	public void visit(Parameter item)
	{
		astParameterCount++;
		defaultHandler(item);
	}
	
		@Override
	public void visit(FunctionDef item)
	{
		astFunctionDefCount++;
		defaultHandler(item);
	}

	@Override
	public void visit(ClassDefStatement item)
	{
		astClassDefStatementCount++;
		defaultHandler(item);
	}

	@Override
	public void visit(IdentifierDeclStatement statementItem)
	{
		astIdentifierDeclStatementCount++;
		defaultHandler(statementItem);
	}

	@Override
	public void visit(ExpressionStatement statementItem)
	{
		astExpressionStatementCount++;
		defaultHandler(statementItem);
	}

	@Override
	public void visit(CallExpression expression)
	{
		astCallExpressionCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(Condition expression)
	{
		astConditionCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(AssignmentExpr expression)
	{
		astAssignmentExprCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(PrimaryExpression expression)
	{
		astPrimaryExpressionCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(Identifier expression)
	{
		astIdentifierCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(MemberAccess expression)
	{
		astMemberAccessCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(UnaryExpression expression)
	{
		astUnaryExpressionCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(Argument expression)
	{
		astArgumentCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(ReturnStatement expression)
	{
		astReturnStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(GotoStatement expression)
	{
		astGotoStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(ContinueStatement expression)
	{
		astContinueStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(BreakStatement expression)
	{
		astBreakStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(CompoundStatement expression)
	{
		astCompoundStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(IfStatement expression)
	{
		astIfStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(ForStatement expression)
	{
		astForStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(WhileStatement expression)
	{
		astWhileStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(DoStatement expression)
	{
		astDoStatementCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(Label expression)
	{
		astLabelCount++;
		defaultHandler(expression);
	}

	@Override
	public void visit(SwitchStatement expression)
	{
		astSwitchStatementCount++;
		defaultHandler(expression);
	}


}