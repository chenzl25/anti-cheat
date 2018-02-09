package ast.statements;

import ast.walking.ASTNodeVisitor;

public class SwitchStatement extends BlockStarter
{
	public void accept(ASTNodeVisitor visitor)
	{
		visitor.visit(this);
	}

	public String getEscapedCodeStr()
	{
		String s = "switch (";
		s += condition.getEscapedCodeStr();
		s += ") {\n";
			s += statement.getEscapedCodeStr();
		s += "}";
		return s;
	}
}
