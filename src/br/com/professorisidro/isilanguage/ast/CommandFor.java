package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandFor extends AbstractCommand{

	public CommandFor(String _inicialID, String _finalID, String _inicialContent, String _finalContent,
			String _exprFor2, ArrayList<AbstractCommand> _exprFor) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
	
		
		str.append("\n}");
		return str.toString();
	}
	
}
