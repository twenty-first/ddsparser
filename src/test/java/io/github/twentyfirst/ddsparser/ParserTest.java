package io.github.twentyfirst.ddsparser;

import org.antlr.v4.runtime.RecognitionException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserTest extends TestBase {

	private static final Logger log = LoggerFactory.getLogger(ParserTest.class);

	private static final String src = 
			"     A          R TESTDDS\n" + 
			"     A            STRING        10          TEXT('STRING FIELD')\n" + 
			"     A            ZONED          8S 0       TEXT('ZONED FIELD')";
	
	public ParserTest() {
		super(log);
	}

	@Test
	public void simpleTest() throws RecognitionException {
		helper.parse(src);
		Assert.assertFalse(helper.isFailed());
	}

}
