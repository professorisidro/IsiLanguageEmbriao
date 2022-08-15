package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandCase extends AbstractCommand {

    private String condition;
    private HashMap<String, ArrayList<AbstractCommand>> caseCommands;
    private ArrayList<AbstractCommand> defaultsCommands;

    public CommandCase(String condition, HashMap<String, ArrayList<AbstractCommand>> caseCommands,
            ArrayList<AbstractCommand> defaultsCommands) {
        this.condition = condition;
        this.caseCommands = caseCommands;
        this.defaultsCommands = defaultsCommands;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();
        str.append("switch (" + condition + ") {\n");
        for (Map.Entry<String, ArrayList<AbstractCommand>> entry : caseCommands.entrySet()) {
            String caseItem = entry.getKey();
            ArrayList<AbstractCommand> commands = entry.getValue();
            str.append("case " + caseItem + ":\n");
            for (AbstractCommand cmd : commands) {
                str.append(cmd.generateJavaCode());
            }
        }
        if (!defaultsCommands.isEmpty()) {
            str.append("default:\n");
            for (AbstractCommand cmd : defaultsCommands) {
                str.append(cmd.generateJavaCode() + "\n");
            }
        }
        str.append("\n}\n");

        return str.toString();
    }

    @Override
    public String generateDartCode() {
        StringBuilder str = new StringBuilder();
        str.append("switch (" + condition + ") {\n");
        for (Map.Entry<String, ArrayList<AbstractCommand>> entry : caseCommands.entrySet()) {
            String caseItem = entry.getKey();
            ArrayList<AbstractCommand> commands = entry.getValue();
            str.append("case " + caseItem + ": {\n");
            for (AbstractCommand cmd : commands) {
                str.append(cmd.generateJavaCode());
            }
            str.append("break;");
            str.append("\n}");
        }
        if (!defaultsCommands.isEmpty()) {
            str.append("default:\n");
            for (AbstractCommand cmd : defaultsCommands) {
                str.append(cmd.generateJavaCode() + "\n");
            }
            str.append("break;");
        }
        str.append("\n}\n");

        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandCase [caseCommands=" + caseCommands + ", condition=" + condition + ", defaultsCommands="
                + defaultsCommands + "]";
    }
}
