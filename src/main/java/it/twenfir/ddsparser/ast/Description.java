package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Description extends AstNode {

	private String description;
	
	public Description(Location location, String description) {
		super(location);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
