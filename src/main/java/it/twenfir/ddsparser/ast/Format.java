package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Format extends AstNode {

    public Format(Location location) {
        super(location);
    }

	public FileName getFileName() {
		return getChild(FileName.class);
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
