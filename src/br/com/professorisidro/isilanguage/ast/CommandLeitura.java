package br.com.professorisidro.isilanguage.ast;

import br.com.professorisidro.isilanguage.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private IsiVariable var;

	public CommandLeitura(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}

	private String getType(IsiVariable var) {
		if (var.getType() == IsiVariable.INT) {
			return "nextInt();";
		} else if (var.getType() == IsiVariable.DOUBLE) {
			return "nextDouble();";
		} else {
			return "nextLine();";
		}
	}

	@Override
	public String generateJavaCode() {
		return id + "= _key." + getType(var);
	}

	@Override
	public String generateDartCode() {
		return id = "stdin.readLineSync(encoding: utf8);";
	}

	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}
