package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.ddsparser.DdsParser.DataTypeContext;

public class DataType extends AstNode {

	private String type;
	private Integer size;
	private Integer precision;
	
	public DataType(DataTypeContext context) {
		super(context);
		type = context.TYPE() != null ? context.TYPE().getText() : null;
		size = Integer.parseInt(context.SIZE(0).getText());
		precision = context.SIZE(1) != null ? Integer.parseInt(context.SIZE(1).getText())  : null;
	}
	
    public String getType() {
		return type;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getPrecision() {
		return precision;
	}

	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
