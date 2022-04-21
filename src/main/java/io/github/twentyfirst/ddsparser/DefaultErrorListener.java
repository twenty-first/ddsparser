package io.github.twentyfirst.ddsparser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;

public class DefaultErrorListener extends BaseErrorListener {

	private Logger log;
	private boolean throwOnError;
	private String name = "ddsparser";
	private String fileName = "input";
	private boolean errors = false;

	public DefaultErrorListener(Logger log) {
		this(false, log);
	}

	public DefaultErrorListener(boolean throwOnError, Logger log) {
		this.throwOnError = throwOnError;
		this.log = log;
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, 
			int charPositionInLine, String msg, RecognitionException e) {
		errors = true;
		log.error(String.format("%s: %s(%d, %d): %s", name, fileName, line, charPositionInLine, msg));
		if ( throwOnError && e != null ) {
			throw e;
		}
	}

	public boolean isThrowOnError() {
		return throwOnError;
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isErrors() {
		return errors;
	}

}
