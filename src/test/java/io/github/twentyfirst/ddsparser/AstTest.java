package io.github.twentyfirst.ddsparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.twentyfirst.ddsparser.ast.Dds;
import io.github.twentyfirst.ddsparser.ast.Field;

public class AstTest extends TestBase {

	static final Logger log = LoggerFactory.getLogger(AstTest.class);

	private static final String src = 
			"     A          R TESTDDS\n" + 
			"     A            STRING        10          TEXT('STRING FIELD')\n" + 
			"     A            ZONED          8S 0       TEXT('ZONED FIELD')";

	public AstTest() {
		super(log);
	}

	@Test
	public void test() {
		Dds ast = helper.ast(src);
		assertEquals("TESTDDS", ast.getRecordFormat());
		assertNotNull(ast);
		assertNotNull(ast.getFields().next());
		Field f = ast.getFields().next();
		assertEquals(new Integer(10), f.getDataType().getSize());
		assertFalse(ast.getKeys().hasNext());
	}
}
