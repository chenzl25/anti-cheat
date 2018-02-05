package ast.statements;

import ast.walking.ASTNodeVisitor;

public class WhileStatement extends BlockStarter
{
	public void accept(ASTNodeVisitor visitor)
	{
		visitor.visit(this);
	}

	public String getEscapedCodeStr()
	{
		String s = "while (";
		s += condition.getEscapedCodeStr();
		s += ") {\n";
		s += statement.getEscapedCodeStr();
		s += "}";
		return s;
	}

}
