package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;

public interface DdsVisitor<ValueT> extends AstVisitor<ValueT> {
    ValueT visit(DataType dataType);
    ValueT visit(Dds dds);
    ValueT visit(Description description);
    ValueT visit(Field field);
    ValueT visit(Heading heading);
    ValueT visit(Key key);
    ValueT visit(Text text);
}
