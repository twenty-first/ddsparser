package it.twenfir.ddsparser.ast.value;

import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.ddsparser.ast.DdsVisitor;

public class LiteralNumber extends Value {

	private String value;
	
	public LiteralNumber(Location location, String value) {
		super(location);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitLiteralNumber(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
