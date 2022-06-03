package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class RefField extends AstNode {

	private String name;
	
	public RefField(Location location, String name) {
		super(location);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
