package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Select extends AstNode {

	private String fieldName;
	
	public Select(Location location, String fieldName) {
		super(location);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	public Comp getComp() {
		return getChild(Comp.class);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitSelect(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
