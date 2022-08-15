grammar IsiLang;

@header{
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
    import java.util.HashMap;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
    private String _exprEnquanto;
    private String _exprCase;
    private ArrayList<AbstractCommand> _caseDefault;
    private HashMap<String, ArrayList<AbstractCommand>> _caseCommands = new HashMap<String, ArrayList<AbstractCommand>>();
    private String _caseCondition; // usado para guardar o case e salvar no hashmap com os commandos correspondentes
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
    private ArrayList<AbstractCommand> listaEnquanto;
    private ArrayList<AbstractCommand> listaCase;
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateJavaCode(){
		program.generateJavaTarget();
	}

    public void generateDartCode() {
        program.generateDartTarget();
    }

    public void checkAttrType(IsiVariable var) {
        boolean isInt = false;
        boolean isDouble = false;
        boolean isString = var.getValue().matches(".*[a-zA-Z].");
        Number number = null;

        // Caso não seja uma String(i.e. apenas letras) assume-se que é um número e testa seu tipo
        if(!isString) {
            try {
                if(var.getValue().indexOf(".") >= 0) {
                        number = Double.parseDouble(var.getValue());
                        isDouble = true;
                } 
                else {
                        number = Integer.parseInt(var.getValue());
                        isInt = true;
                }
            }
            catch(NumberFormatException ex) {
                throw new IsiSemanticException("Invalid type on variable " + var.getName());
            }
        } 

        // Pega o tipo esperado pela variável
        String targetType = "";
        if(var.getType() == 0) {
               targetType = "int";
        }
        else if(var.getType() == 1) {
                targetType = "double";
        }
        else {
                targetType = "String";
        }

        // Pega qual tipo recebido pela variável
        String gotType = "";
        if(isString) {
                gotType = "String";
        }
        else if(isInt) {
                gotType = "int";
        }
        else if(isDouble) {
                gotType = "double";
        }

        if(isInt && var.getType() != IsiVariable.INT) {
            throw new IsiSemanticException("Type mismatch on variable " + var.getName() + ". Expected " + targetType + " but got " + gotType);
        }
        if(isDouble && var.getType() != IsiVariable.DOUBLE) {
            throw new IsiSemanticException("Type mismatch on variable " + var.getName() + ". Expected " + targetType + " but got " + gotType);
        }
        if(isString && var.getType() != IsiVariable.TEXT) {
            throw new IsiSemanticException("Type mismatch on variable " + var.getName() + ". Expected " + targetType + " but got " + gotType);
        }
    }

    // Checa se a condição do switch não é double
    public void checkSwitchType(String id) {
            IsiVariable var = (IsiVariable) symbolTable.get(id);
            if(var.getType() == IsiVariable.DOUBLE) {
                throw new IsiSemanticException("Cannot switch on a double type variable");
            }
    }

    // Checa se os tipos dos cases batem com o switch
    public void checkCaseType(int targetType, String condition) {
        boolean isInt = condition.matches("[0-9]*");
        boolean isString = condition.matches(".*[a-zA-Z].");
        
        if(!isString && ! isInt) {
            throw new IsiSemanticException("You can only switch String and int");
        }

        if(!isInt && targetType == IsiVariable.INT) {
            throw new IsiSemanticException("Case type must be the same of the switch variable");
        }
        else if(!isString && targetType == IsiVariable.TEXT) {
            throw new IsiSemanticException("Case type must be the same of the switch variable");
        }
    }
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	 
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } 
              (  VIR 
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )* 
               SC
           ;
           
tipo       : 'int' { _tipo = IsiVariable.INT;  }
           | 'double' { _tipo = IsiVariable.DOUBLE; }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdleitura  
 		|  cmdescrita 
 		|  cmdattrib
 		|  cmdselecao  
        |  cmdenquanto
        |  cmdcase
		;
		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } 
                     FP 
                     SC 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP 
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
                    }
                 FP 
                 SC
               {
               	    CommandEscrita cmd = new CommandEscrita(_writeID);
                    stack.peek().add(cmd);
               }
			;
			
cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                   } 
               ATTR { _exprContent = ""; } 
               expr 
               SC
               {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 stack.peek().add(cmd);
                 IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
                 var.setValue(_exprContent);
                 checkAttrType(var);
               }
			;
			
			
cmdselecao  :  'se' AP
                    (ID | expr) { _exprDecision = _input.LT(-1).getText(); }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | expr) {_exprDecision += _input.LT(-1).getText(); }
                    FP 
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
                       listaTrue = stack.pop();	
                    } 
                   ('senao' 
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;

cmdenquanto : 'enquanto' AP 
              (ID | expr) {_exprEnquanto = _input.LT(-1).getText();} 
              OPREL {_exprEnquanto += _input.LT(-1).getText();}
              (ID | expr) {_exprEnquanto += _input.LT(-1).getText();}
              FP 
              ACH 
              {
                curThread = new ArrayList<AbstractCommand>();
                stack.push(curThread);
              }
              (cmd)+ 
              FCH
              {
                listaEnquanto = stack.pop();
                CommandEnquanto cmd = new CommandEnquanto(_exprEnquanto, listaEnquanto);
                stack.peek().add(cmd);
              }
            ;
            
cmdcase     : 'escolha' AP
              ID {
                    checkSwitchType(_input.LT(-1).getText());
                    _exprCase = _input.LT(-1).getText();
                 }
              FP
              ACH
              (
                'caso'
                (INT | STRING) {
                                    _caseCondition = _input.LT(-1).getText();
                                    IsiVariable var = (IsiVariable) symbolTable.get(_exprCase);
                                    checkCaseType(var.getType(), _caseCondition);
                               }
                COLON
                {
                    curThread = new ArrayList<AbstractCommand>();
                    stack.push(curThread);
                }
                (cmd)+
                'break'
                SC
                {
                    listaCase = stack.pop();
                    _caseCommands.put(_caseCondition, listaCase);
                }
              )+
              (
                'padrao'
                COLON
                {
                    curThread = new ArrayList<AbstractCommand>();
                    stack.push(curThread);
                }
                (cmd)+
                'break'
                SC
                {
                    _caseDefault = stack.pop();
                    CommandCase cmd = new CommandCase(_exprCase, _caseCommands, _caseDefault);
                    stack.peek().add(cmd);
                }
              )?
              FCH
            ;

expr : termo expr_;

expr_ : (
        OPSOMA {_exprContent += '+';} termo expr_
        | OPSUB {_exprContent += '-';} termo expr_
      )?
      ;

termo : fator termo_;

termo_ : (
        OPMUL {_exprContent += '*';} fator termo_ 
        | OPDIV {_exprContent += '/';} fator termo_ 
      )?
      ;

fator : INT {
                _exprContent += _input.LT(-1).getText();
            }
        |
        DOUBLE {
                _exprContent += _input.LT(-1).getText();
            }
        | STRING {
                    _exprContent += _input.LT(-1).getText();
                 } 
        | ID {
                verificaID(_input.LT(-1).getText());
                _exprContent += _input.LT(-1).getText();
            }
        | AP {_exprContent += _input.LT(-1).getText();}
          expr
          FP {_exprContent += _input.LT(-1).getText();}
      ;
	
OPSOMA: '+'
      ;

OPSUB: '-'
     ;

OPMUL: '*'
     ;

OPDIV: '/'
    ;

AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
COLON  : ':'
       ;

OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : '='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	 
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
DOUBLE	: [0-9]+ ('.' [0-9]+)
		;

INT     : [0-9]+
        ;

STRING  : '"' ( '\\"' | . )*? '"'
        ;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;
