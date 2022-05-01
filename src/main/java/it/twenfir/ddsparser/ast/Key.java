package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Key extends AstNode {

	private String fieldName;
	
	public Key(Location location, String fieldName) {
		super(location);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
