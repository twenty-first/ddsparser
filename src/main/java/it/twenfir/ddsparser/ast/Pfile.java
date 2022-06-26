package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Pfile extends AstNode {

	private String pfile;

    public Pfile(Location location, String pfile) {
        super(location);
		this.pfile = pfile;
    }

	public String getPfile() {
		return pfile;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitPfile(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
