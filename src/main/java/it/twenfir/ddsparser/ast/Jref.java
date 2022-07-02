package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Jref extends AstNode {

	private int index;
	
	public Jref(Location location, int index) {
		super(location);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitJref(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
