package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandCaso extends AbstractCommand {

    private String condition;
    private HashMap<String, ArrayList<AbstractCommand>> caseCommands;
    private ArrayList<AbstractCommand> defaultsCommands;

    public CommandCaso(String condition, HashMap<String, ArrayList<AbstractCommand>> caseCommands,
            ArrayList<AbstractCommand> defaultsCommands) {
        this.condition = condition;
        this.caseCommands = caseCommands;
        this.defaultsCommands = defaultsCommands;
    }

    @Override
    public String generateJavaCode() {
        // TODO Auto-generated method stub
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
        if(!defaultsCommands.isEmpty()) {
            str.append("default:\n");
            for (AbstractCommand cmd: defaultsCommands) {
                str.append(cmd.generateJavaCode());
            }
        }

        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandCaso [caseCommands=" + caseCommands + ", condition=" + condition + ", defaultsCommands="
                + defaultsCommands + "]";
    }
}
