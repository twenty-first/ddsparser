package io.github.twentyfirst.ddsparser;

import static org.junit.Assert.assertThrows;

import org.antlr.v4.runtime.RecognitionException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserTest extends TestBase {

	private static final Logger log = LoggerFactory.getLogger(ParserTest.class);

	private static final String goodSrc = 
			"     A          R TESTDDS\n" + 
			"     A            STRING        10          TEXT('STRING FIELD')\n" + 
			"     A            ZONED          8S 0       TEXT('ZONED FIELD')";

	private static final String badSrc = 
			"     A          R BADDDS\n" + 
			"     A            STRING        10";
	
	public ParserTest() {
		super(log);
	}

	@Test
	public void simpleTest() throws RecognitionException {
		helper.parse(goodSrc);
		Assert.assertFalse(helper.isFailed());
	}

	@Test
	public void errorTest() {
		assertThrows(ParseException.class, () -> helper.parse(badSrc));
	}
}
