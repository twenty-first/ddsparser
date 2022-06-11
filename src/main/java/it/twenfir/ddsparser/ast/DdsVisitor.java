package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;

public interface DdsVisitor<ValueT> extends AstVisitor<ValueT> {
    ValueT visitDataType(DataType dataType);
    ValueT visitDds(Dds dds);
    ValueT visitDescription(Description description);
    ValueT visitDescriptionElement(DescriptionElement descriptionElement);
    ValueT visitEditWord(EditWord editWord);
    ValueT visitField(Field field);
    ValueT visitHeading(Heading heading);
    ValueT visitKey(Key key);
    ValueT visitRefField(RefField refField);
    ValueT visitText(Text text);
    ValueT visitValues(Values values);
}
