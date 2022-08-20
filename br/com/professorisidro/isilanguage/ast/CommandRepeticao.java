package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
	
	private String expr;
	private ArrayList<AbstractCommand> list = new ArrayList<AbstractCommand>();
	
	public CommandRepeticao(String expr, ArrayList<AbstractCommand> list) {
		this.expr = expr;
		this.list = list;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while ("+expr+") {\n");
		
		for (AbstractCommand cmd: list) {
			str.append(cmd.generateJavaCode());
		}
		
		str.append("}");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepeticao [repetition=" + expr + ", list=" + list + "]";
	}
	
}
