// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import java.util.ArrayList;
	import java.util.Stack;

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
		T__9=10, T__10=11, EXPONENTIAL=12, LOGARITHM=13, SQUAREROOT=14, AP=15, 
		FP=16, SC=17, OP=18, CIRFLEX=19, ATTR=20, VIR=21, ACH=22, FCH=23, OPREL=24, 
		ID=25, NUMBER=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "EXPONENTIAL", "LOGARITHM", "SQUAREROOT", "AP", "FP", 
		"SC", "OP", "CIRFLEX", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'booleano'", 
		"'leia'", "'escreva'", "'se'", "'senao'", "'true'", "'false'", null, null, 
		null, "'('", "')'", "';'", null, "'^'", "'='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"EXPONENTIAL", "LOGARITHM", "SQUAREROOT", "AP", "FP", "SC", "OP", "CIRFLEX", 
		"ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", "WS"
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
		private String _exprMath;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private String _varBase;
		private String _varIndex;
		private String _varLog;
		private String _varSqrt;	
		
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
		
		public String mathExprExp(String _varBase, String _varIndex){
	       return "Math.pow(" + _varBase + "," + _varIndex + ")";
		}
		
		public String mathExprLog(String _varLog){
	       return "Math.log(" + _varLog + ")";
		}
		
		public String mathExprSqrt(String _varSqrt){
	       return "Math.sqrt(" + _varSqrt + ")";
		}
		
		public void generateCode(){
			program.generateTarget();
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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 11:
			EXPONENTIAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 12:
			LOGARITHM_action((RuleContext)_localctx, actionIndex);
			break;
		case 13:
			SQUAREROOT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void EXPONENTIAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 verificaID(_input.LT(-1).getText()); 
			                              _varBase = _input.LT(-1).getText(); 
			                            
			break;
		case 1:
			 verificaID(_input.LT(-1).getText()); 
			                              _varIndex = _input.LT(-1).getText(); 
			                            
			break;
		}
	}
	private void LOGARITHM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 verificaID(_input.LT(-1).getText());
			                            _varLog = _input.LT(-1).getText(); 
			                          
			break;
		}
	}
	private void SQUAREROOT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 verificaID(_input.LT(-1).getText());
			                              _varSqrt = _input.LT(-1).getText(); 
			                            
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00de\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\5\r"+
		"\u0086\n\r\3\r\3\r\3\r\3\r\5\r\u008c\n\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\5\16\u0098\n\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00a5\n\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00c5\n\31\3\32\3\32"+
		"\7\32\u00c9\n\32\f\32\16\32\u00cc\13\32\3\33\6\33\u00cf\n\33\r\33\16\33"+
		"\u00d0\3\33\3\33\6\33\u00d5\n\33\r\33\16\33\u00d6\5\33\u00d9\n\33\3\34"+
		"\3\34\3\34\3\34\2\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\35\3\2\b\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5"+
		"\2\13\f\17\17\"\"\2\u00e9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\39\3\2\2\2\5B\3\2\2\2\7K\3\2\2\2\tR\3\2\2\2\13X\3\2\2\2\ra\3"+
		"\2\2\2\17f\3\2\2\2\21n\3\2\2\2\23q\3\2\2\2\25w\3\2\2\2\27|\3\2\2\2\31"+
		"\u0082\3\2\2\2\33\u0090\3\2\2\2\35\u009c\3\2\2\2\37\u00a9\3\2\2\2!\u00ab"+
		"\3\2\2\2#\u00ad\3\2\2\2%\u00af\3\2\2\2\'\u00b1\3\2\2\2)\u00b3\3\2\2\2"+
		"+\u00b5\3\2\2\2-\u00b7\3\2\2\2/\u00b9\3\2\2\2\61\u00c4\3\2\2\2\63\u00c6"+
		"\3\2\2\2\65\u00ce\3\2\2\2\67\u00da\3\2\2\29:\7r\2\2:;\7t\2\2;<\7q\2\2"+
		"<=\7i\2\2=>\7t\2\2>?\7c\2\2?@\7o\2\2@A\7c\2\2A\4\3\2\2\2BC\7h\2\2CD\7"+
		"k\2\2DE\7o\2\2EF\7r\2\2FG\7t\2\2GH\7q\2\2HI\7i\2\2IJ\7=\2\2J\6\3\2\2\2"+
		"KL\7p\2\2LM\7w\2\2MN\7o\2\2NO\7g\2\2OP\7t\2\2PQ\7q\2\2Q\b\3\2\2\2RS\7"+
		"v\2\2ST\7g\2\2TU\7z\2\2UV\7v\2\2VW\7q\2\2W\n\3\2\2\2XY\7d\2\2YZ\7q\2\2"+
		"Z[\7q\2\2[\\\7n\2\2\\]\7g\2\2]^\7c\2\2^_\7p\2\2_`\7q\2\2`\f\3\2\2\2ab"+
		"\7n\2\2bc\7g\2\2cd\7k\2\2de\7c\2\2e\16\3\2\2\2fg\7g\2\2gh\7u\2\2hi\7e"+
		"\2\2ij\7t\2\2jk\7g\2\2kl\7x\2\2lm\7c\2\2m\20\3\2\2\2no\7u\2\2op\7g\2\2"+
		"p\22\3\2\2\2qr\7u\2\2rs\7g\2\2st\7p\2\2tu\7c\2\2uv\7q\2\2v\24\3\2\2\2"+
		"wx\7v\2\2xy\7t\2\2yz\7w\2\2z{\7g\2\2{\26\3\2\2\2|}\7h\2\2}~\7c\2\2~\177"+
		"\7n\2\2\177\u0080\7u\2\2\u0080\u0081\7g\2\2\u0081\30\3\2\2\2\u0082\u0085"+
		"\5\37\20\2\u0083\u0086\5\65\33\2\u0084\u0086\5\63\32\2\u0085\u0083\3\2"+
		"\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\r\2\2\u0088"+
		"\u008b\5\'\24\2\u0089\u008c\5\65\33\2\u008a\u008c\5\63\32\2\u008b\u0089"+
		"\3\2\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\r\3\2\u008e"+
		"\u008f\5!\21\2\u008f\32\3\2\2\2\u0090\u0091\7n\2\2\u0091\u0092\7q\2\2"+
		"\u0092\u0093\7i\2\2\u0093\u0094\3\2\2\2\u0094\u0097\5\37\20\2\u0095\u0098"+
		"\5\65\33\2\u0096\u0098\5\63\32\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2"+
		"\2\u0098\u0099\3\2\2\2\u0099\u009a\b\16\4\2\u009a\u009b\5!\21\2\u009b"+
		"\34\3\2\2\2\u009c\u009d\7u\2\2\u009d\u009e\7s\2\2\u009e\u009f\7t\2\2\u009f"+
		"\u00a0\7v\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a4\5\37\20\2\u00a2\u00a5\5"+
		"\65\33\2\u00a3\u00a5\5\63\32\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2"+
		"\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\17\5\2\u00a7\u00a8\5!\21\2\u00a8\36"+
		"\3\2\2\2\u00a9\u00aa\7*\2\2\u00aa \3\2\2\2\u00ab\u00ac\7+\2\2\u00ac\""+
		"\3\2\2\2\u00ad\u00ae\7=\2\2\u00ae$\3\2\2\2\u00af\u00b0\t\2\2\2\u00b0&"+
		"\3\2\2\2\u00b1\u00b2\7`\2\2\u00b2(\3\2\2\2\u00b3\u00b4\7?\2\2\u00b4*\3"+
		"\2\2\2\u00b5\u00b6\7.\2\2\u00b6,\3\2\2\2\u00b7\u00b8\7}\2\2\u00b8.\3\2"+
		"\2\2\u00b9\u00ba\7\177\2\2\u00ba\60\3\2\2\2\u00bb\u00c5\t\3\2\2\u00bc"+
		"\u00bd\7@\2\2\u00bd\u00c5\7?\2\2\u00be\u00bf\7>\2\2\u00bf\u00c5\7?\2\2"+
		"\u00c0\u00c1\7?\2\2\u00c1\u00c5\7?\2\2\u00c2\u00c3\7#\2\2\u00c3\u00c5"+
		"\7?\2\2\u00c4\u00bb\3\2\2\2\u00c4\u00bc\3\2\2\2\u00c4\u00be\3\2\2\2\u00c4"+
		"\u00c0\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\62\3\2\2\2\u00c6\u00ca\t\4\2"+
		"\2\u00c7\u00c9\t\5\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\64\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd"+
		"\u00cf\t\6\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d8\3\2\2\2\u00d2\u00d4\7\60\2\2\u00d3"+
		"\u00d5\t\6\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d2\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\66\3\2\2\2\u00da\u00db\t\7\2\2\u00db\u00dc\3\2\2"+
		"\2\u00dc\u00dd\b\34\6\2\u00dd8\3\2\2\2\r\2\u0085\u008b\u0097\u00a4\u00c4"+
		"\u00c8\u00ca\u00d0\u00d6\u00d8\7\3\r\2\3\r\3\3\16\4\3\17\5\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}