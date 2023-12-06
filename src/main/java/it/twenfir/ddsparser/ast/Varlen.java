package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Varlen extends AstNode {

	private Integer size;

	public Varlen(Location location, Integer size) {
		super(location);
		this.size = size;
	}

	public Integer getSize() {
		return size;
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitVarlen(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
