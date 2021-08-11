package br.com.professorisidro.isilanguage.ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	
	public CommandEscrita(String id) {
		this.id = id;
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return "System.out.println("+id+");";
	}
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}
	

}
