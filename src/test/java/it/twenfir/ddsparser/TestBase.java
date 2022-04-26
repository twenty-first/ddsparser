package it.twenfir.ddsparser;

import org.slf4j.Logger;

public class TestBase {

	protected Helper helper;
	
	protected TestBase(Logger log) {
		this.helper = new Helper(log);
	}

}
