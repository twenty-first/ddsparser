package it.twenfir.ddsparser;

import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.antlr.exception.ParseException;

public class ParserUnitTests extends TestBase {

	private static final Logger log = LoggerFactory.getLogger(ParserUnitTests.class);
	
	public ParserUnitTests() {
		super(log);
	}

	@Test
	public void simpleTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A            ZONED          8S 0       TEXT('ZONED FIELD')";

		helper.parse(src);
	}

	@Test
	public void symbolTest() throws ParseException {
		String src = 
				"     A          R TEST$\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A            ZONED          8S 0       TEXT('ZONED FIELD')";

		helper.parse(src);
	}

	@Test
	public void colhdgTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A            ZONED          8S 0       COLHDG('ZONED')";

		helper.parse(src);
	}

	@Test
	public void textColhdgTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A                                      COLHDG('ZONED')";

		helper.parse(src);
	}

	@Test
	public void uniqueTest() throws ParseException {
		String src = 
				"     A                                      UNIQUE\n" + 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')";

		helper.parse(src);
	}

	@Test
	public void textDescendingKeyTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A          K STRING                    DESCEND";

		helper.parse(src);
	}

	@Test
	public void errorTest() {
		String src = 
				"     A          R BADDDS\n" + 
				"     A            STRING        ";
		
		assertThrows(ParseException.class, () -> helper.parse(src));
	}
}
