package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Format extends AstNode {

	private String format;

    public Format(Location location, String format) {
        super(location);
		this.format = format;
    }

	public String getFormat() {
		return format;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitFormat(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
