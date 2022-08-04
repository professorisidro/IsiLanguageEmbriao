package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;
import java.util.HashMap;

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
        return null;
    }

    @Override
    public String toString() {
        return "CommandCaso [caseCommands=" + caseCommands + ", condition=" + condition + ", defaultsCommands="
                + defaultsCommands + "]";
    }
}
