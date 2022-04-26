package it.twenfir.ddsparser.ast;

import it.twenfir.ddsparser.DdsParser.FieldContext;

public class Field extends AstNode {

	private String name;
	private String heading;
	private String description;
	
	public Field(FieldContext context) {
		super(context);
		name = context.IDENTIFIER().getText();
		heading = context.colhdg().size() > 0 ? context.colhdg(0).DESCRIPTION().getText() : null;
		description = context.text().size() > 0 ? context.text(0).DESCRIPTION().getText() : null;
		addChild(new DataType(context.dataType()));
	}
	
    public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getHeading() {
		return heading;
	}
	
	public DataType getDataType() {
		return new ChildrenIterator<DataType>(getChildren(), DataType.class).next();
	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
