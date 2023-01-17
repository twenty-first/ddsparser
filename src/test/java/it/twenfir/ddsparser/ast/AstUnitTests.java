package it.twenfir.ddsparser.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
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
		assertEquals(Integer.valueOf(10), f.getDataType().getSize());
		assertFalse(ast.getKeys().hasNext());
	}

	@Test
	public void dateFieldTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A            DATE            Z         TEXT('DATE FIELD')";

		Dds ast = helper.ast(src);
		Iterator<Field> iter = ast.getFields();
		iter.next();
		DataType dt = iter.next().getDataType();
		assertEquals("Z", dt.getType());
		assertNull(dt.getSize());
	}

	@Test
	public void formatTest() throws ParseException {
		String src = 
				"     A          R TESTDDS                   FORMAT(EXTFMT)";

		Dds ast = helper.ast(src);
		assertEquals("EXTFMT", ast.getFormat().getFileName().getName());
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
				"     A            STRING1       10          TEXT('STRING1 FIELD')\n" + 
				"     A            STRING2       10          TEXT('STRING2 FIELD')\n" + 
				"     A          K STRING1                   DESCEND\n" +
				"     A          K STRING2";

		Dds ast = helper.ast(src);
		Iterator<Key> ki = ast.getKeys(); 
		Key k = ki.next();
		assertTrue(k.isDescending());
		assertFalse(ki.next().isDescending());
	}

	@Test
	public void splitPlusDescriptionTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING +\n" +
				"     A                                           FIELD')";

		Dds ast = helper.ast(src);
		assertEquals("STRING FIELD", ast.getFields().next().getText().getDescription().getDescription());
	}

	@Test
	public void splitMinusDescriptionTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING-\n" +
				"     A                                       FIELD')";

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
		Iterator<Value> iter = ast.getFields().next().getValues().getValues();
		while ( iter.hasNext() ) { 
			String v = iter.next().getString();
			assertTrue(v.equals("Y") || v.equals("N"));
		}
	}

	@Test
	public void refsTest() throws ParseException {
		String src = 
				"                                            REF(REFERENCE)\n" + 
				"                R TESTDDS\n" + 
				"                  REFERRAL  R               REFFLD(REFERRED)\n" +
				"                  REFFIELD  R               REFFLD(REFFIELD REFFILE)";

		Dds ast = helper.ast(src);
		assertEquals("REFERENCE", ast.getRef().getReference());
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
		assertEquals("12345", ast.getFields().next().getCcsid().getCcsid());
	}

	@Test
	public void editCodeTest() throws ParseException {
		String src = 
				"     A          R TESTDDS\n" + 
				"     A            STRING        10          TEXT('STRING FIELD')\n" + 
				"     A                                      EDTCDE(K)";

		Dds ast = helper.ast(src);
		assertEquals("K", ast.getFields().next().getEditCode().getEditCode());
	}

	@Test
	public void missingPrecisionTest() throws ParseException {
		String src =
				"     A          R TESTDDS\n" +
				"     A            FIELD          8S";
		Dds ast = helper.ast(src);
		assertEquals(0, ast.getFields().next().getDataType().getPrecision());
	}

	@Test
	public void sstTest() throws ParseException {
		String src =
				"     A          R TESTDDS\n" +
				"     A            FIELD              I      SST(BASE  1 10)";
		Dds ast = helper.ast(src);
		assertEquals("I", ast.getFields().next().getUsage());
		assertEquals("BASE", ast.getFields().next().getSst().getField());
	}
}
