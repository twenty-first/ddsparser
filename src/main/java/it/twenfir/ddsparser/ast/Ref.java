package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.parser.ast.CommonRef;

public class Ref extends CommonRef {
	
	public Ref(Location location, String library, String reference) {
		super(location, library, reference);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitRef(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
