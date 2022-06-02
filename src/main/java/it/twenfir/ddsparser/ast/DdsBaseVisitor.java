package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.BaseAstVisitor;

public abstract class DdsBaseVisitor<ValueT> extends BaseAstVisitor<ValueT> implements DdsVisitor<ValueT> {

    @Override
    public ValueT visit(DataType node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(Dds node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(Description node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(DescriptionElement node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(EditWord node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(Field node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(Heading node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(Key node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visit(Text node) {
        return visitChildren(node);
    }

}
