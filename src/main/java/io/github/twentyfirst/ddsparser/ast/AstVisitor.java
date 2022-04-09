package io.github.twentyfirst.ddsparser.ast;

public interface AstVisitor<ValueT> {

	ValueT defaultValue();
	ValueT aggregate(ValueT accumulator, ValueT value);

	ValueT visit(AstNode node);
	ValueT visitChildren(AstNode node);

}
