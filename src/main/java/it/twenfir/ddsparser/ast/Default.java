package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Default extends AstNode {

	public Default(Location location) {
		super(location);
	}

	public Description getDescription() {
		return getChild(Description.class);

	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitDefault(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
