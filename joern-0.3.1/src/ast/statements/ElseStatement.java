package ast.statements;

public class ElseStatement extends BlockStarter
{
	public String getEscapedCodeStr()
	{
		String s = "";
		s += " else {\n";
		if (statement != null) {
			s += statement.getEscapedCodeStr();
		}
		s += "}";
		return s;
	}
}
