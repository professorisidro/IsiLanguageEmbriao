// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;	
	import java.util.ArrayList;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, AP=7, FP=8, SC=9, OP=10, 
		ATTR=11, VIR=12, ID=13, NUMBER=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "AP", "FP", "SC", "OP", 
		"ATTR", "VIR", "ID", "NUMBER", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
		"'('", "')'", "';'", null, "'='", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "AP", "FP", "SC", "OP", "ATTR", 
		"VIR", "ID", "NUMBER", "WS"
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
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\7\16\\\n\16\f\16\16\16_\13\16\3\17\6\17b\n\17\r\17\16\17c\3\17\3"+
		"\17\6\17h\n\17\r\17\16\17i\5\17l\n\17\3\20\3\20\3\20\3\20\2\2\21\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"\3\2\7\5\2,-//\61\61\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2t"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5*\3\2"+
		"\2\2\7\63\3\2\2\2\t:\3\2\2\2\13@\3\2\2\2\rE\3\2\2\2\17M\3\2\2\2\21O\3"+
		"\2\2\2\23Q\3\2\2\2\25S\3\2\2\2\27U\3\2\2\2\31W\3\2\2\2\33Y\3\2\2\2\35"+
		"a\3\2\2\2\37m\3\2\2\2!\"\7r\2\2\"#\7t\2\2#$\7q\2\2$%\7i\2\2%&\7t\2\2&"+
		"\'\7c\2\2\'(\7o\2\2()\7c\2\2)\4\3\2\2\2*+\7h\2\2+,\7k\2\2,-\7o\2\2-.\7"+
		"r\2\2./\7t\2\2/\60\7q\2\2\60\61\7i\2\2\61\62\7=\2\2\62\6\3\2\2\2\63\64"+
		"\7p\2\2\64\65\7w\2\2\65\66\7o\2\2\66\67\7g\2\2\678\7t\2\289\7q\2\29\b"+
		"\3\2\2\2:;\7v\2\2;<\7g\2\2<=\7z\2\2=>\7v\2\2>?\7q\2\2?\n\3\2\2\2@A\7n"+
		"\2\2AB\7g\2\2BC\7k\2\2CD\7c\2\2D\f\3\2\2\2EF\7g\2\2FG\7u\2\2GH\7e\2\2"+
		"HI\7t\2\2IJ\7g\2\2JK\7x\2\2KL\7c\2\2L\16\3\2\2\2MN\7*\2\2N\20\3\2\2\2"+
		"OP\7+\2\2P\22\3\2\2\2QR\7=\2\2R\24\3\2\2\2ST\t\2\2\2T\26\3\2\2\2UV\7?"+
		"\2\2V\30\3\2\2\2WX\7.\2\2X\32\3\2\2\2Y]\t\3\2\2Z\\\t\4\2\2[Z\3\2\2\2\\"+
		"_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\34\3\2\2\2_]\3\2\2\2`b\t\5\2\2a`\3\2\2"+
		"\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2dk\3\2\2\2eg\7\60\2\2fh\t\5\2\2gf\3\2"+
		"\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ke\3\2\2\2kl\3\2\2\2l\36\3"+
		"\2\2\2mn\t\6\2\2no\3\2\2\2op\b\20\2\2p \3\2\2\2\b\2[]cik\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}