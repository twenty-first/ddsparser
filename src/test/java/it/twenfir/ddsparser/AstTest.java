package it.twenfir.ddsparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.ddsparser.ast.Dds;
import it.twenfir.ddsparser.ast.Field;

public class AstTest extends TestBase {

	static final Logger log = LoggerFactory.getLogger(AstTest.class);

	public AstTest() {
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
}
