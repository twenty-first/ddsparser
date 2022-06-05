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
		assertEquals("TESTDDS", ast.getRecord());
		assertNotNull(ast);
		assertNotNull(ast.getFields().next());
		Field f = ast.getFields().next();
		assertEquals(new Integer(10), f.getDataType().getSize());
		assertFalse(ast.getKeys().hasNext());
	}

	@Test
	public void formatTest() throws ParseException {
		String src = 
				"     A          R TESTDDS                   FORMAT(EXTFMT)";

		Dds ast = helper.ast(src);
		assertEquals("EXTFMT", ast.getFormat());
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
		assertEquals("ZONED", f.getHeading().getDescription().getDescription());
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
		assertTrue(f.getText().getDescription() != null && f.getHeading() != null);
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

	@Test
	public void textDescendingKeyTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A          K STRING                    DESCEND";

		Dds ast = helper.ast(src);
		Key k = ast.getKeys().next();
		assertTrue(k.isDescending());
	}

	@Test
	public void splitDescriptionTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING +\n" +
				"     A                                           FIELD')";

		Dds ast = helper.ast(src);
		assertEquals("STRING FIELD", ast.getFields().next().getText().getDescription().getDescription());
	}

	@Test
	public void multipleDescriptionTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING ' +\n" + 
				"     A                                           'FIELD')";

		Dds ast = helper.ast(src);
		assertEquals("STRING FIELD", ast.getFields().next().getText().getDescription().getDescription());
	}

	@Test
	public void valuesTest() throws ParseException {
		String src = 
				"                R TESTDDS\n" + 
				"                  ANSWER         1          VALUES('Y' 'N')";

		Dds ast = helper.ast(src);
		ast.getFields().next().getValues().getValues().forEach((v) -> { assertTrue(v.equals("Y") || v.equals("N")); });
	}

	@Test
	public void refsTest() throws ParseException {
		String src = 
				"                                            REF(REFERENCE)\n" + 
				"                R TESTDDS\n" + 
				"                  REFERRAL       R          REFFLD(REFERRED)\n" +
				"                  REFFIELD       R          REFFLD(REFFIELD REFFILE)";

		Dds ast = helper.ast(src);
		assertEquals("REFERENCE", ast.getReference());
		Iterator<Field> iter = ast.getFields();
		assertEquals("REFERRED", iter.next().getRefField().getName());
		assertEquals("REFFILE", iter.next().getRefField().getFile());
	}

	@Test
	public void recordTextTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A                                      TEXT('RECORD TEXT')\n" + 
				"     A            STRING        10";

		Dds ast = helper.ast(src);
		assertEquals("RECORD TEXT", ast.getText().getDescription().getDescription());
	}

	@Test
	public void ccsidTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A                                      CCSID(12345)";

		Dds ast = helper.ast(src);
		assertEquals("12345", ast.getFields().next().getCcsid());
	}

	@Test
	public void editCodeTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A                                      EDTCDE(K)";

		Dds ast = helper.ast(src);
		assertEquals("K", ast.getFields().next().getEditCode());
	}
}
