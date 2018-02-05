package ast.statements;

import ast.walking.ASTNodeVisitor;

public class DoStatement extends BlockStarter
{
	public void accept(ASTNodeVisitor visitor)
	{
		visitor.visit(this);
	}

	public String getEscapedCodeStr()
	{
		String s = "do {\n";
		s += statement.getEscapedCodeStr();
		s += "} while (" + condition.getEscapedCodeStr() + ")\n";
		return s;
	}
}
