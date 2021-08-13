package br.com.professorisidro.isilanguage.ast;

public class CommandOpEsp extends AbstractCommand{

    private String termo;
    private String va1;
    private String op;
    private String va2;
    
    public CommandOpEsp(String termo) { 
        this.termo = termo;
    }
    
    private void getVariables() {
        String[] variable = termo.split(" ");
        va1 = variable[0];
        op = variable[1];
        va2 = variable[2];
    }
    
    @Override
    public String generateJavaCode() {
        getVariables();
        if (op.equalsIgnoreCase("^")) {
            // TODO Auto-generated method stub
            return "Math.pow("+ Integer.parseInt(va1) + "," + Integer.parseInt(va2) +")";
        }
        else if (op.equalsIgnoreCase("%")) {
            // TODO Auto-generated method stub
            return "("+ Integer.parseInt(va1) + "%" + Integer.parseInt(va2) +")";
        }
        // TODO Auto-generated method stub
        return "";
    }
    
    @Override
    public String toString() {
        return "CommandOpEspecial [va1=" + va1 + ", op=" + op + ", va2=" + va2+ "]";
    }
}
