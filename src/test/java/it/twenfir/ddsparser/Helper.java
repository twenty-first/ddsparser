package it.twenfir.ddsparser;

import org.slf4j.Logger;

import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.ast.Dds;

public class Helper {

	Logger log;
	
	public Helper(Logger log) {
		this.log = log;
	}
	
	private DdsParserDriver driver(String src) {
		return new DdsParserDriver(src);
	}
	
	public DdsContext parse(String src) {
		DdsParserDriver d = driver(src);
		DdsContext dc = d.parse();
		return dc;
	}

	public Dds ast(String src) {
		DdsParserDriver d = driver(src);
		Dds dds = d.makeAst();
		return dds;
	}
}
