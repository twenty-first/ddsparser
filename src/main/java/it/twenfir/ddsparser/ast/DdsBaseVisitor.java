package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.BaseAstVisitor;

public abstract class DdsBaseVisitor<ValueT> extends BaseAstVisitor<ValueT> implements DdsVisitor<ValueT> {

    @Override
    public ValueT visitCcsid(Ccsid node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitDataType(DataType node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitDds(Dds node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitDefault(Default node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitDescription(Description node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitDescriptionElement(DescriptionElement node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitEditCode(EditCode node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitEditWord(EditWord node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitField(Field node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitHeading(Heading node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitKey(Key node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitRef(Ref node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitRefField(RefField node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitText(Text node) {
        return visitChildren(node);
    }

    @Override
    public ValueT visitValues(Values node) {
        return visitChildren(node);
    }

}
