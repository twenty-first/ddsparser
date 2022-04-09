package io.github.twentyfirst.ddsparser.ast;

import io.github.twentyfirst.ddsparser.DdsParser.KeyContext;

public class Key extends AstNode {

	private String fieldName;
	
	public Key(KeyContext context) {
		super(context);
		fieldName = context.IDENTIFIER().getText();
	}

	public String getFieldName() {
		return fieldName;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
