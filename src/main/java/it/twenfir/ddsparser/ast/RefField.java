package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.parser.ast.CommonRefField;

public class RefField extends CommonRefField {
	
	public RefField(Location location, String name, String library, String file) {
		super(location, name, library, file);
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitRefField(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
