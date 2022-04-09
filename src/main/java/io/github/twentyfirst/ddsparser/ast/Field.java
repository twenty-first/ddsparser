package io.github.twentyfirst.ddsparser.ast;

import io.github.twentyfirst.ddsparser.DdsParser.FieldContext;

public class Field extends AstNode {

	private String name;
	private String description;
	
	public Field(FieldContext context) {
		super(context);
		name = context.IDENTIFIER().getText();
		description = context.DESCRIPTION().getText();
		addChild(new DataType(context.dataType()));
	}
	
    public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public DataType getDataType() {
		return new ChildrenIterator<DataType>(getChildren(), DataType.class).next();
	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
