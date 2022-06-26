package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Altseq extends AstNode {

	private String altseq;

    public Altseq(Location location, String altseq) {
        super(location);
		this.altseq = altseq;
    }

	public String getAltseq() {
		return altseq;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitAltseq(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
