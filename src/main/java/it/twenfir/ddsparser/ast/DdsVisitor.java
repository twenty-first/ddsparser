package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;

public interface DdsVisitor<ValueT> extends AstVisitor<ValueT> {
    ValueT visit(Dds dds);
    ValueT visit(Field field);
    ValueT visit(DataType dataType);
    ValueT visit(Key key);
}
