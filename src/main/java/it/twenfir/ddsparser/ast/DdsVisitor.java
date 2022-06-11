package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;

public interface DdsVisitor<ValueT> extends AstVisitor<ValueT> {
    ValueT visitCcsid(Ccsid node);
    ValueT visitDataType(DataType node);
    ValueT visitDds(Dds node);
    ValueT visitDescription(Description node);
    ValueT visitDescriptionElement(DescriptionElement node);
    ValueT visitEditCode(EditCode node);
    ValueT visitEditWord(EditWord node);
    ValueT visitField(Field node);
    ValueT visitHeading(Heading node);
    ValueT visitKey(Key node);
    ValueT visitRefField(RefField node);
    ValueT visitText(Text node);
    ValueT visitValues(Values node);
}
