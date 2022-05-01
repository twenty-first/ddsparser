package it.twenfir.ddsparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.antlr.parser.LoggingTokenSource;
import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.ast.Dds;

public class Driver {

	private static Logger log = LoggerFactory.getLogger(Driver.class);
	
	private DefaultErrorListener errorListener;
	private CommonTokenStream tokenStream;
	private DdsParser parser;
	private DdsContext parseTree;
	
	public Driver(String ddsSource) {
		errorListener = new DefaultErrorListener(log);
        ANTLRInputStream inputStream = new ANTLRInputStream(ddsSource);
        DdsLexer lexer = new DdsLexer(inputStream);
    	lexer.removeErrorListeners();
    	lexer.addErrorListener(errorListener);
        LoggingTokenSource source = new LoggingTokenSource(lexer);
        tokenStream = new CommonTokenStream(source);
        parser = new DdsParser(tokenStream);
    	parser.removeErrorListeners();
    	parser.addErrorListener(errorListener);
	}
	
    public DdsContext parse() {
    	try {
        	if ( parseTree == null ) {
                parseTree = parser.dds();
        	}
        	if ( errorListener.isErrors() ) {
        		throw new ParseException("Parse failed");
        	}
            return parseTree;
    	}
    	catch ( RecognitionException e ) {
    		throw new ParseException("Parse failed", e);
    	}
    }

    public Dds makeAst() {
//        ParseTreeWalker walker = new ParseTreeWalker();
        DdsContext tree = parse();
        AstBuilder builder = new AstBuilder();
//        walker.walk(builder, tree);
//        return builder.getDds();
		Dds dds = builder.visitDds(tree);
		return dds;
    }
}
