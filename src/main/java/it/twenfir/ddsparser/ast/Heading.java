package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.ChildrenIterator;
import it.twenfir.antlr.ast.Location;

public class Heading extends AstNode {

	public Heading(Location location) {
		super(location);
	}

	public Description getDescription() {
		return new ChildrenIterator<Description>(getChildren(), Description.class).next();

	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
