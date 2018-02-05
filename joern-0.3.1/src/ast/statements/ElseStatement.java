package ast.statements;

public class ElseStatement extends BlockStarter
{
	public String getEscapedCodeStr()
	{
		String s = "";
		s += " else {\n";
		s += statement.getEscapedCodeStr();
		s += "}";
		return s;
	}
}
