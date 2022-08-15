package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {
    private String condition;
    private ArrayList<AbstractCommand> commands;

    public CommandEnquanto(String condition, ArrayList<AbstractCommand> commands) {
        this.condition = condition;
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "CommandEnquanto [commands=" + commands + ", condition=" + condition + "]";
    }

    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();
        str.append("while (" + condition + ") {\n");
        for (AbstractCommand cmd : commands) {
            str.append(cmd.generateJavaCode());
        }
        str.append("\n}\n");

        return str.toString();
    }

    @Override
    public String generateDartCode() {
        StringBuilder str = new StringBuilder();
        str.append("while (" + condition + ") {\n");
        for (AbstractCommand cmd : commands) {
            str.append(cmd.generateJavaCode());
        }
        str.append("\n}\n");

        return str.toString();
    }

}
