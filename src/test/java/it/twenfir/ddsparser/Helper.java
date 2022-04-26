package it.twenfir.ddsparser;

import org.slf4j.Logger;

import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.ast.Dds;

public class Helper {

	Logger log;
	
	public Helper(Logger log) {
		this.log = log;
	}
	
	private Driver driver(String src) {
		return new Driver(src);
	}
	
	public DdsContext parse(String src) {
		Driver d = driver(src);
		DdsContext dc = d.parse();
		return dc;
	}

	public Dds ast(String src) {
		Driver d = driver(src);
		Dds dds = d.makeAst();
		return dds;
	}
}
