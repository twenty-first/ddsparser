package io.github.twentyfirst.ddsparser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;

import io.github.twentyfirst.ddsparser.DdsParser.DdsContext;
import io.github.twentyfirst.ddsparser.ast.Dds;

public class Helper extends BaseErrorListener {

	Logger log;
	boolean failed = false;
	
	public Helper(Logger log) {
		this.log = log;
	}
	
	private Driver driver(String src) {
		failed = false;
		return new Driver(src, this);
	}
	
	public DdsContext parse(String src) {
		Driver d = driver(src);
		DdsContext dc = d.parse();
		return failed ? null : dc;
	}

	public Dds ast(String src) {
		Driver d = driver(src);
		Dds dds = d.makeAst();
		return failed ? null : dds;
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
			int charPositionInLine, String msg, RecognitionException e) {
		log.error(String.format("(%d, %d): %s", line, charPositionInLine, msg));
		failed = true;
	}
	
	boolean isFailed() {
		return failed;
	}
}
