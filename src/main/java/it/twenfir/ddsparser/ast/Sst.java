package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Sst extends AstNode {

	private String field;
	private Integer from;
	private Integer length;

    public Sst(Location location, String field, Integer from, Integer length) {
        super(location);
		this.field = field;
		this.from = from;
		this.length = length;
    }

	public String getField() {
		return field;
	}
	
    public Integer getFrom() {
		return from;
	}

	public Integer getLength() {
		return length;
	}

	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitSst(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
