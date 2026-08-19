package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.ddsparser.ast.value.Identifier;

public class Jfld extends AstNode {
	
	public Jfld(Location location) {
		super(location);
	}
	
	Iterator<Identifier> getFields() {
		return getChildren(Identifier.class);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitJfld(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
