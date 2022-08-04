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
    private String _caseCondition; // usado para guardar o case e salver no hashmap com os commandos correspondentes
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
	
	public void generateCode(){
		program.generateTarget();
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
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
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
        |  cmdcaso
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
            
cmdcaso     : 'escolha' AP
              (ID | NUMBER | STRING) {_exprCase = _input.LT(-1).getText();}
              FP
              ACH
              (
                'caso'
                (ID | NUMBER | STRING) {_caseCondition = _input.LT(-1).getText();}
                COLON
                {
                    curThread = new ArrayList<AbstractCommand>();
                    stack.push(curThread);
                }
                (cmd)+
                'break'
                SC
                {
                    System.out.println("case");
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
                    System.out.println("default");
                    _caseDefault = stack.pop();
                    CommandCaso cmd = new CommandCaso(_exprCase, _caseCommands, _caseDefault);
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

fator : NUMBER {
                _exprContent += _input.LT(-1).getText();
               }
        | STRING {
                 _exprContent += _input.LT(-1).getText();
                 } 
        | ID {
                verificaID(_input.LT(-1).getText());
                IsiVariable var = (IsiVariable) symbolTable.get(_input.LT(-1).getText());
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
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;

STRING  : '"' ( '\\"' | . )*? '"'
        ;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;
