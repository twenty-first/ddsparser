package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Select extends AstNode {

	private String fieldName;
	private boolean all;
	
	public Select(Location location, String fieldName, boolean all) {
		super(location);
		this.fieldName = fieldName;
		this.all = all;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	public boolean isAll() {
		return all;
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
