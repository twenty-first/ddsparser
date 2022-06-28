package it.twenfir.ddsparser.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.antlr.exception.ParseException;
import it.twenfir.ddsparser.TestBase;

public class VisitorUnitTests extends TestBase {
    
    private static Logger log = LoggerFactory.getLogger(VisitorUnitTests.class);
    
    public VisitorUnitTests() {
        super(log);
    }
    
    @Test
    public void simpleTest() {
        DdsBaseVisitor<Integer> visitor = new DdsBaseVisitor<Integer>() {

            @Override
            public Integer aggregate(Integer accumulator, Integer value) {
                return accumulator + value;
            }

            @Override
            public Integer defaultValue() {
                return 1;
            }
             
        };

        Key k = new Key(null, null, false);
        DataType t = new DataType(null, null, null, null);
        Field f = new Field(null, null, false);
        f.addChild(t);
        Dds d = new Dds(null, null, false);
        d.addChild(f);
        d.addChild(k);
        int i = visitor.visit(d);
        assertEquals(4, i);
    }

	@Test
	public void reffldTest() throws ParseException {
		String src = 
				"                                            REF(REFERENCE)\n" + 
				"                R TESTDDS\n" + 
				"                  REFERRAL       R          REFFLD(REFERRED)\n" +
				"                  REFFIELD       R          REFFLD(REFFIELD REFFILE)";

		Dds dds = helper.ast(src);
    	DdsVisitor<? extends Integer> visitor = new DdsBaseVisitor<Integer>() {

            @Override
            public Integer aggregate(Integer accumulator, Integer value) {
                return accumulator + value;
            }

            @Override
            public Integer defaultValue() {
                return 0;
            }

			@Override
			public Integer visitRefField(RefField node) {
				return visitChildren(node) + 1;
			}

    	};
    	int i = visitor.visitDds(dds);
    	assertEquals(2, i);
	}}
