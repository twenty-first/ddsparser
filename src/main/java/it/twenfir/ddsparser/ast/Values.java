package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Values extends AstNode {
	
	public Values(Location location) {
		super(location);
	}

	public Iterator<Value> getValues() {
		return getChildren(Value.class);
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitValues(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
