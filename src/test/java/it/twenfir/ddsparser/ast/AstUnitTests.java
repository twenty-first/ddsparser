package it.twenfir.ddsparser.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.antlr.exception.ParseException;
import it.twenfir.ddsparser.TestBase;

public class AstUnitTests extends TestBase {

	static final Logger log = LoggerFactory.getLogger(AstUnitTests.class);

	public AstUnitTests() {
		super(log);
	}

	@Test
	public void simpleTest() {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A            ZONED          8S 0       TEXT('ZONED FIELD')";

		Dds ast = helper.ast(src);
		assertNotNull(ast);
		assertEquals("TESTDDS", ast.getRecordFormat());
		assertNotNull(ast);
		assertNotNull(ast.getFields().next());
		Field f = ast.getFields().next();
		assertEquals(new Integer(10), f.getDataType().getSize());
		assertFalse(ast.getKeys().hasNext());
	}

	@Test
	public void colhdgTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A            ZONED          8S 0       COLHDG('ZONED')";

		Dds ast = helper.ast(src);
		Iterator<Field> iter = ast.getFields();
		iter.next();
		Field f = iter.next();
		assertEquals("ZONED", f.getHeading());
	}

	@Test
	public void textColhdgTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A                                      COLHDG('ZONED')";

		Dds ast = helper.ast(src);
		Iterator<Field> iter = ast.getFields();
		Field f = iter.next();
		assertTrue(f.getDescription() != null && f.getHeading() != null);
	}

	@Test
	public void uniqueTest() throws ParseException {
		String src = 
				"     A                                      UNIQUE\n" + 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')";

		Dds ast = helper.ast(src);
		assertTrue(ast.isUnique());
	}
}
