package it.twenfir.ddsparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.antlr.exception.ParseException;
import it.twenfir.antlr.parser.LoggingTokenSource;
import it.twenfir.antlr.parser.ParserDriverBase;
import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.ast.Dds;

public class DssParserDriver extends ParserDriverBase {

	private static Logger log = LoggerFactory.getLogger(DssParserDriver.class);
	
	private CommonTokenStream tokenStream;
	private DdsParser parser;
	private DdsContext parseTree;
	
	public DssParserDriver(String ddsSource) {
		super("dssparser", log);
        ANTLRInputStream inputStream = new ANTLRInputStream(ddsSource);
        DdsLexer lexer = new DdsLexer(inputStream);
    	lexer.removeErrorListeners();
    	lexer.addErrorListener(this);
        LoggingTokenSource source = new LoggingTokenSource(lexer);
        tokenStream = new CommonTokenStream(source);
        parser = new DdsParser(tokenStream);
    	parser.removeErrorListeners();
    	parser.addErrorListener(this);
	}
	
    public DdsContext parse() {
		if ( parseTree == null ) {
			parseTree = parser.dds();
		}
		if ( isErrors() ) {
			throw new ParseException("Parse failed");
		}
		return parseTree;
    }

    public Dds makeAst() {
        DdsContext tree = parse();
        AstBuilder builder = new AstBuilder();
		Dds dds = builder.visitDds(tree);
		return dds;
    }
    
    public TokenStream getTokenStream() {
    	return tokenStream;
    }
}
