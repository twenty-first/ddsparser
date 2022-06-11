package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Ccsid extends AstNode {

	private String ccsid;

    public Ccsid(Location location, String ccsid) {
        super(location);
		this.ccsid = ccsid;
    }

	public String getCcsid() {
		return ccsid;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitCcsid(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
