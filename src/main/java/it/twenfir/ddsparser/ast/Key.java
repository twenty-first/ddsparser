package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Key extends AstNode {

	private String fieldName;
	private boolean descending;
	
	public Key(Location location, String fieldName, boolean descending) {
		super(location);
		this.fieldName = fieldName;
		this.descending = descending;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	public boolean isDescending() {
		return descending;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
