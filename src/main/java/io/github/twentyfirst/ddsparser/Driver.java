package io.github.twentyfirst.ddsparser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import io.github.twentyfirst.ddsparser.DdsParser.DdsContext;
import io.github.twentyfirst.ddsparser.ast.Dds;

public class Driver {

	private CommonTokenStream tokenStream;
	private DdsParser parser;
	private DdsContext parseTree;
	
	public Driver(String statement) {
		this(statement, null);
	}
		
	public Driver(String statement, ANTLRErrorListener errorListener) {
        ANTLRInputStream inputStream = new ANTLRInputStream(statement);
        DdsLexer lexer = new DdsLexer(inputStream);
        if ( errorListener != null ) {
        	lexer.removeErrorListeners();
        	lexer.addErrorListener(errorListener);
        }
        DdsTokenSource source = new DdsTokenSource(lexer);
        tokenStream = new CommonTokenStream(source);
        parser = new DdsParser(tokenStream);
        if ( errorListener != null ) {
        	parser.removeErrorListeners();
        	parser.addErrorListener(errorListener);
        }
	}
	
    public DdsContext parse() {
    	if ( parseTree == null ) {
            parseTree = parser.dds();
    	}
        return parseTree;
    }

    public Dds makeAst() {
        ParseTreeWalker walker = new ParseTreeWalker();
        DdsContext tree = parse();
        AstBuilder builder = new AstBuilder();
        walker.walk(builder, tree);
        return builder.getDds();

    }
}
