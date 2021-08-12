package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandPara extends AbstractCommand{
	
	private String varFor;
	private String inicialFor;
	private String finalFor;
	private String exprIncrementoFor;
	private ArrayList<AbstractCommand> listaFor;

	

	public CommandPara(String varFor, String inicialFor, String finalFor, String exprIncrementoFor, ArrayList<AbstractCommand> listaFor) {
		this.varFor = varFor;
		this.inicialFor = inicialFor;
		this.finalFor = finalFor;
		this.exprIncrementoFor = exprIncrementoFor;
		this.listaFor = listaFor;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		
		
		
		
		str.append("for ("+varFor +" = "+ inicialFor+";"+varFor+" <= " +finalFor+";"+varFor+" = "+exprIncrementoFor+") {\n");
		for (AbstractCommand cmd: listaFor) {
			str.append(cmd.generateJavaCode());
		}
		str.append("\n}");
		
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandPara ["+ varFor +"="+ inicialFor + "até"+"<="+ finalFor+" Incremento:"+exprIncrementoFor + listaFor+ "]";
	}
	
}
