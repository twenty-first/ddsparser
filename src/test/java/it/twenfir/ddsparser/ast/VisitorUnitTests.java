package it.twenfir.ddsparser.ast;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VisitorUnitTests {
    
    @Test
    public void test() {
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
        Field f = new Field(null, null, null, false);
        f.addChild(t);
        Dds d = new Dds(null, null, null, null, false);
        d.addChild(f);
        d.addChild(k);
        int i = visitor.visit(d);
        assertEquals(4, i);
    }
}
