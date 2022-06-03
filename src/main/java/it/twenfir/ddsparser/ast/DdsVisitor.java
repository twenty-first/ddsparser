package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;

public interface DdsVisitor<ValueT> extends AstVisitor<ValueT> {
    ValueT visit(DataType dataType);
    ValueT visit(Dds dds);
    ValueT visit(Description description);
    ValueT visit(DescriptionElement descriptionElement);
    ValueT visit(EditWord editWord);
    ValueT visit(Field field);
    ValueT visit(Heading heading);
    ValueT visit(Key key);
    ValueT visit(RefField refField);
    ValueT visit(Text text);
    ValueT visit(Values values);
}
