package ast.statements;

import ast.walking.ASTNodeVisitor;

public class GotoStatement extends JumpStatement
{
	public String getTarget()
	{
		return getChild(0).getEscapedCodeStr();
	}

	public void accept(ASTNodeVisitor visitor)
	{
		visitor.visit(this);
	}

	public String getEscapedCodeStr()
	{
		String s = "goto ";
		s += getTarget().substring(0, getTarget().length()-1) + ";\n";
		return s;
	}
}
