// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
    import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, OPSOMA=15, OPSUB=16, 
		OPMUL=17, OPDIV=18, AP=19, FP=20, SC=21, COLON=22, OP=23, ATTR=24, VIR=25, 
		ACH=26, FCH=27, OPREL=28, ID=29, DOUBLE=30, INT=31, STRING=32, WS=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "OPSOMA", "OPSUB", "OPMUL", 
		"OPDIV", "AP", "FP", "SC", "COLON", "OP", "ATTR", "VIR", "ACH", "FCH", 
		"OPREL", "ID", "DOUBLE", "INT", "STRING", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'int'", "'double'", "'texto'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'escolha'", "'caso'", "'break'", 
		"'padrao'", "'+'", "'-'", "'*'", "'/'", "'('", "')'", "';'", "':'", null, 
		"'='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "OPSOMA", "OPSUB", "OPMUL", "OPDIV", "AP", "FP", "SC", 
		"COLON", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "DOUBLE", "INT", 
		"STRING", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00ec\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\5\35\u00c5\n\35\3\36\3\36\7\36\u00c9\n\36\f\36\16\36"+
		"\u00cc\13\36\3\37\6\37\u00cf\n\37\r\37\16\37\u00d0\3\37\3\37\6\37\u00d5"+
		"\n\37\r\37\16\37\u00d6\3 \6 \u00da\n \r \16 \u00db\3!\3!\3!\3!\7!\u00e2"+
		"\n!\f!\16!\u00e5\13!\3!\3!\3\"\3\"\3\"\3\"\3\u00e3\2#\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\b\5"+
		"\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2\u00f5"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5N\3\2\2"+
		"\2\7W\3\2\2\2\t[\3\2\2\2\13b\3\2\2\2\rh\3\2\2\2\17m\3\2\2\2\21u\3\2\2"+
		"\2\23x\3\2\2\2\25~\3\2\2\2\27\u0087\3\2\2\2\31\u008f\3\2\2\2\33\u0094"+
		"\3\2\2\2\35\u009a\3\2\2\2\37\u00a1\3\2\2\2!\u00a3\3\2\2\2#\u00a5\3\2\2"+
		"\2%\u00a7\3\2\2\2\'\u00a9\3\2\2\2)\u00ab\3\2\2\2+\u00ad\3\2\2\2-\u00af"+
		"\3\2\2\2/\u00b1\3\2\2\2\61\u00b3\3\2\2\2\63\u00b5\3\2\2\2\65\u00b7\3\2"+
		"\2\2\67\u00b9\3\2\2\29\u00c4\3\2\2\2;\u00c6\3\2\2\2=\u00ce\3\2\2\2?\u00d9"+
		"\3\2\2\2A\u00dd\3\2\2\2C\u00e8\3\2\2\2EF\7r\2\2FG\7t\2\2GH\7q\2\2HI\7"+
		"i\2\2IJ\7t\2\2JK\7c\2\2KL\7o\2\2LM\7c\2\2M\4\3\2\2\2NO\7h\2\2OP\7k\2\2"+
		"PQ\7o\2\2QR\7r\2\2RS\7t\2\2ST\7q\2\2TU\7i\2\2UV\7=\2\2V\6\3\2\2\2WX\7"+
		"k\2\2XY\7p\2\2YZ\7v\2\2Z\b\3\2\2\2[\\\7f\2\2\\]\7q\2\2]^\7w\2\2^_\7d\2"+
		"\2_`\7n\2\2`a\7g\2\2a\n\3\2\2\2bc\7v\2\2cd\7g\2\2de\7z\2\2ef\7v\2\2fg"+
		"\7q\2\2g\f\3\2\2\2hi\7n\2\2ij\7g\2\2jk\7k\2\2kl\7c\2\2l\16\3\2\2\2mn\7"+
		"g\2\2no\7u\2\2op\7e\2\2pq\7t\2\2qr\7g\2\2rs\7x\2\2st\7c\2\2t\20\3\2\2"+
		"\2uv\7u\2\2vw\7g\2\2w\22\3\2\2\2xy\7u\2\2yz\7g\2\2z{\7p\2\2{|\7c\2\2|"+
		"}\7q\2\2}\24\3\2\2\2~\177\7g\2\2\177\u0080\7p\2\2\u0080\u0081\7s\2\2\u0081"+
		"\u0082\7w\2\2\u0082\u0083\7c\2\2\u0083\u0084\7p\2\2\u0084\u0085\7v\2\2"+
		"\u0085\u0086\7q\2\2\u0086\26\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089\7"+
		"u\2\2\u0089\u008a\7e\2\2\u008a\u008b\7q\2\2\u008b\u008c\7n\2\2\u008c\u008d"+
		"\7j\2\2\u008d\u008e\7c\2\2\u008e\30\3\2\2\2\u008f\u0090\7e\2\2\u0090\u0091"+
		"\7c\2\2\u0091\u0092\7u\2\2\u0092\u0093\7q\2\2\u0093\32\3\2\2\2\u0094\u0095"+
		"\7d\2\2\u0095\u0096\7t\2\2\u0096\u0097\7g\2\2\u0097\u0098\7c\2\2\u0098"+
		"\u0099\7m\2\2\u0099\34\3\2\2\2\u009a\u009b\7r\2\2\u009b\u009c\7c\2\2\u009c"+
		"\u009d\7f\2\2\u009d\u009e\7t\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7q\2\2"+
		"\u00a0\36\3\2\2\2\u00a1\u00a2\7-\2\2\u00a2 \3\2\2\2\u00a3\u00a4\7/\2\2"+
		"\u00a4\"\3\2\2\2\u00a5\u00a6\7,\2\2\u00a6$\3\2\2\2\u00a7\u00a8\7\61\2"+
		"\2\u00a8&\3\2\2\2\u00a9\u00aa\7*\2\2\u00aa(\3\2\2\2\u00ab\u00ac\7+\2\2"+
		"\u00ac*\3\2\2\2\u00ad\u00ae\7=\2\2\u00ae,\3\2\2\2\u00af\u00b0\7<\2\2\u00b0"+
		".\3\2\2\2\u00b1\u00b2\t\2\2\2\u00b2\60\3\2\2\2\u00b3\u00b4\7?\2\2\u00b4"+
		"\62\3\2\2\2\u00b5\u00b6\7.\2\2\u00b6\64\3\2\2\2\u00b7\u00b8\7}\2\2\u00b8"+
		"\66\3\2\2\2\u00b9\u00ba\7\177\2\2\u00ba8\3\2\2\2\u00bb\u00c5\t\3\2\2\u00bc"+
		"\u00bd\7@\2\2\u00bd\u00c5\7?\2\2\u00be\u00bf\7>\2\2\u00bf\u00c5\7?\2\2"+
		"\u00c0\u00c1\7?\2\2\u00c1\u00c5\7?\2\2\u00c2\u00c3\7#\2\2\u00c3\u00c5"+
		"\7?\2\2\u00c4\u00bb\3\2\2\2\u00c4\u00bc\3\2\2\2\u00c4\u00be\3\2\2\2\u00c4"+
		"\u00c0\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5:\3\2\2\2\u00c6\u00ca\t\4\2\2"+
		"\u00c7\u00c9\t\5\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb<\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd"+
		"\u00cf\t\6\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\7\60\2\2\u00d3"+
		"\u00d5\t\6\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7>\3\2\2\2\u00d8\u00da\t\6\2\2\u00d9\u00d8"+
		"\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"@\3\2\2\2\u00dd\u00e3\7$\2\2\u00de\u00df\7^\2\2\u00df\u00e2\7$\2\2\u00e0"+
		"\u00e2\13\2\2\2\u00e1\u00de\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5\3"+
		"\2\2\2\u00e3\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00e7\7$\2\2\u00e7B\3\2\2\2\u00e8\u00e9\t\7\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00eb\b\"\2\2\u00ebD\3\2\2\2\13\2\u00c4\u00c8\u00ca"+
		"\u00d0\u00d6\u00db\u00e1\u00e3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}