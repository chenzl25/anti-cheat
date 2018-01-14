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
import java.util.Map;
import java.util.HashMap;

public class ASTEncoder extends ASTNodeVisitor {
	private Integer astParameterListCount = 0;
	private Integer astParameterCount = 0;
	private Integer astFunctionDefCount = 0;
	private Integer astClassDefStatementCount = 0;
	private Integer astIdentifierDeclStatementCount = 0;
	private Integer astExpressionStatementCount = 0;
	private Integer astCallExpressionCount = 0;
	private Integer astConditionCount = 0;
	private Integer astAssignmentExprCount = 0;
	private Integer astPrimaryExpressionCount = 0;
	private Integer astIdentifierCount = 0;
	private Integer astMemberAccessCount = 0;
	private Integer astUnaryExpressionCount = 0;
	private Integer astArgumentCount = 0;
	private Integer astReturnStatementCount = 0;
	private Integer astGotoStatementCount = 0;
	private Integer astContinueStatementCount = 0;
	private Integer astBreakStatementCount = 0;
	private Integer astCompoundStatementCount = 0;
	private Integer astIfStatementCount = 0;
	private Integer astForStatementCount = 0;
	private Integer astWhileStatementCount = 0;
	private Integer astDoStatementCount = 0;
	private Integer astLabelCount = 0;
	private Integer astSwitchStatementCount = 0;

	private void init() {
		astParameterListCount = 0;
		astParameterCount = 0;
		astFunctionDefCount = 0;
		astClassDefStatementCount = 0;
		astIdentifierDeclStatementCount = 0;
		astExpressionStatementCount = 0;
		astCallExpressionCount = 0;
		astConditionCount = 0;
		astAssignmentExprCount = 0;
		astPrimaryExpressionCount = 0;
		astIdentifierCount = 0;
		astMemberAccessCount = 0;
		astUnaryExpressionCount = 0;
		astArgumentCount = 0;
		astReturnStatementCount = 0;
		astGotoStatementCount = 0;
		astContinueStatementCount = 0;
		astBreakStatementCount = 0;
		astCompoundStatementCount = 0;
		astIfStatementCount = 0;
		astForStatementCount = 0;
		astWhileStatementCount = 0;
		astDoStatementCount = 0;
		astLabelCount = 0;
		astSwitchStatementCount = 0;
	}

	private void encodeImpl(ASTNode node) {
		FunctionDatabaseNode function = new FunctionDatabaseNode();
		function.initialize(node);
		FunctionDef astRoot = (FunctionDef) function.getASTRoot();
		visit(astRoot);
	}

	public Map encodeToMap(ASTNode node) {
		init();
		encodeImpl(node);
		return prettyMap();
	}

	public String encodeToString(ASTNode node) {
		init();
		encodeImpl(node);
		return prettyString();
	}

	private Map prettyMap() {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("astParameterListCount",astParameterListCount);
		m.put("astParameterCount",astParameterCount);
		m.put("astFunctionDefCount",astFunctionDefCount);
		m.put("astClassDefStatementCount",astClassDefStatementCount);
		m.put("astIdentifierDeclStatementCount",astIdentifierDeclStatementCount);
		m.put("astExpressionStatementCount",astExpressionStatementCount);
		m.put("astCallExpressionCount",astCallExpressionCount);
		m.put("astConditionCount",astConditionCount);
		m.put("astAssignmentExprCount",astAssignmentExprCount);
		m.put("astPrimaryExpressionCount",astPrimaryExpressionCount);
		m.put("astIdentifierCount",astIdentifierCount);
		m.put("astMemberAccessCount",astMemberAccessCount);
		m.put("astUnaryExpressionCount",astUnaryExpressionCount);
		m.put("astArgumentCount",astArgumentCount);
		m.put("astReturnStatementCount",astReturnStatementCount);
		m.put("astGotoStatementCount",astGotoStatementCount);
		m.put("astContinueStatementCount",astContinueStatementCount);
		m.put("astBreakStatementCount",astBreakStatementCount);
		m.put("astCompoundStatementCount",astCompoundStatementCount);
		m.put("astIfStatementCount",astIfStatementCount);
		m.put("astForStatementCount",astForStatementCount);
		m.put("astWhileStatementCount",astWhileStatementCount);
		m.put("astDoStatementCount",astDoStatementCount);
		m.put("astLabelCount",astLabelCount);
		m.put("astSwitchStatementCount",astSwitchStatementCount);
		return m;
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
		s.append("</ASTEncoder>");
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