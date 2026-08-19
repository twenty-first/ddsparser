package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.ddsparser.ast.value.LiteralNumber;
import it.twenfir.ddsparser.ast.value.LiteralString;

public class Value extends AstNode {
	
	public Value(Location location) {
		super(location);
	}

	public LiteralString getString() {
		return getChild(LiteralString.class);
	}

	public LiteralNumber getNumber() {
		return getChild(LiteralNumber.class);
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitValue(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
