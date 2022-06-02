package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class EditWord extends AstNode {

	public EditWord(Location location) {
		super(location);
	}

	public Description getDescription() {
		return getChild(Description.class);
	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
