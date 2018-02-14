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
import ast.statements.ElseStatement;
import databaseNodes.FunctionDatabaseNode;
import ast.walking.ASTNodeVisitor;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


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
		ParameterList parameterList = item.getParameterList();
		CompoundStatement compoundStatement = new CompoundStatement();
		LinkedList<Parameter> parameters = parameterList.getParameters();
		Iterator<Parameter> i = parameters.iterator();
		for (; i.hasNext();)
		{
			Random rand = new Random();
			int  n = rand.nextInt(3);
			Parameter param = i.next();
			if (n == 0) { // 1/3 
				i.remove();
				IdentifierDeclStatement identifierDeclStatement = new IdentifierDeclStatement();
				identifierDeclStatement.setCodeStr(param.getEscapedCodeStr() + ";");
				compoundStatement.addChild(identifierDeclStatement);
			}
		}
		compoundStatement.addChild(item.getContent());
		item.setContent(compoundStatement);
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
		ASTNode parent = parentStack.peek();
		Integer index = indexStack.peek();
		WhileStatement whileStatement = new WhileStatement();
		whileStatement.setCondition(expression.getCondition());
		whileStatement.setStatement(expression.getStatement());
		whileStatement.getStatement().addChild(new BreakStatement());
		parent.setChild(index, whileStatement);
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
		/*
		switch (expr) {
			case 1: {
				statement
				break;
				statement
			}
			case 2:
			statements
			default:
			statements
		}

		if (expr == 1) {
			statement
		} else if (expr == 2) {
			statements
		} else {
			statements
		}

		while ( expr == 1) {
			statement
		}
		while (expr == 2) {
			statement
		}
		while (!(expr == 1) && !(expr == 2)) {
			statement
		}
		*/
		defaultHandler(expression);
		ASTNode parent = parentStack.peek();
		Integer index = indexStack.peek();
		Random rand = new Random();
		int  n = rand.nextInt(2);
		if (n == 0) {
			CompoundStatement compoundStatement = new CompoundStatement();
			ArrayList<String> condition_string_arr = new ArrayList<>();
			for (int i = 0; i < expression.getStatement().getChildCount(); i++) {
				if (expression.getStatement().getChild(i) instanceof Label) {
					Label label = (Label) expression.getStatement().getChild(i);
					WhileStatement whileStatement = new WhileStatement();
					String case_string = label.getEscapedCodeStr();
					int com_index = case_string.length();
					for (int j = 0; j < case_string.length(); j++) {
						if (case_string.charAt(j) == ':') {
							com_index = j;
							break;
						}
					}
					if (label.getEscapedCodeStr().charAt(0) == 'c') {
						int begin_index = 4;
						String expr = label.getEscapedCodeStr().substring(4, com_index);
						Condition condition = new Condition();
						String condition_string = expression.getCondition().getEscapedCodeStr() + " == " + "(" + expr + ")";
						condition.setCodeStr(condition_string);
						condition_string_arr.add(condition_string);
						whileStatement.setCondition(condition);
					} else {
						String default_string = "true";
						for (String s : condition_string_arr) {
							default_string += " && ";
							default_string += "!( " + s + ")";
						}
						Condition condition = new Condition();
						condition.setCodeStr(default_string);
						whileStatement.setCondition(condition);
					}
					CompoundStatement inCompoundStatement = new CompoundStatement();
					for (int j = i + 1; j < expression.getStatement().getChildCount(); j++) {
						if (!(expression.getStatement().getChild(j) instanceof Label)) {
							inCompoundStatement.addChild(expression.getStatement().getChild(j));
						} else {
							i = j-1;
							break;
						}
					}
					whileStatement.setStatement(inCompoundStatement);
					compoundStatement.addChild(whileStatement);
				}
			}
			parent.setChild(index, compoundStatement);
		} else if (n == 1) {
			IfStatement rootIfStatement = null;
			IfStatement ifStatement = null;
			ElseStatement elseStatement = null;
			for (int i = 0; i < expression.getStatement().getChildCount(); i++) {
				if (expression.getStatement().getChild(i) instanceof Label) {
					Label label = (Label) expression.getStatement().getChild(i);
					String case_string = label.getEscapedCodeStr();
					int com_index = case_string.length();
					for (int j = 0; j < case_string.length(); j++) {
						if (case_string.charAt(j) == ':') {
							com_index = j;
							break;
						}
					}
					if (label.getEscapedCodeStr().charAt(0) == 'c') {
						int begin_index = 4;
						String expr = label.getEscapedCodeStr().substring(4, com_index);
						Condition condition = new Condition();
						String condition_string = expression.getCondition().getEscapedCodeStr() + " == " + "(" + expr + ")";
						condition.setCodeStr(condition_string);
						ifStatement = new IfStatement();
						ifStatement.addChild(condition);
					} else {
						// default:
						String default_string = "true";
						Condition condition = new Condition();	
						condition.setCodeStr(default_string);
						ifStatement = new IfStatement();
						ifStatement.addChild(condition);
					}
					CompoundStatement inCompoundStatement = new CompoundStatement();
					boolean reach_break = false;
					for (int j = i + 1; j < expression.getStatement().getChildCount(); j++) {
						if (!(expression.getStatement().getChild(j) instanceof Label)) {
							if (expression.getStatement().getChild(j) instanceof BreakStatement) {
								reach_break = true;
							}
							if (!reach_break) {
								inCompoundStatement.addChild(expression.getStatement().getChild(j));
							}
						} else {
							i = j-1;
							break;
						}
					}
					ifStatement.addChild(inCompoundStatement);
					if (rootIfStatement == null) {
						rootIfStatement = ifStatement;
					} else {
						elseStatement.setStatement(ifStatement);
					}
					elseStatement = new ElseStatement();
					// ifStatement.addChild(elseStatement);
					ifStatement.setElseNode(elseStatement);
				}
			}
			parent.setChild(index, rootIfStatement);
		}
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