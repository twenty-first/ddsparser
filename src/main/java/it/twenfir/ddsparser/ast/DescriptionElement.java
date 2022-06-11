package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class DescriptionElement extends AstNode {

	private String description;
	
	public DescriptionElement(Location location, String description) {
		super(location);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitDescriptionElement(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
