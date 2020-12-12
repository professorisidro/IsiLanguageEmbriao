package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> listaEnquanto;
	
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> listaEnquanto) {
		this.condition = condition;
		this.listaEnquanto = listaEnquanto;
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandEnquanto [condition=" + condition + ", listaEnquanto" + listaEnquanto
				+ "]";
	}
	
	

}