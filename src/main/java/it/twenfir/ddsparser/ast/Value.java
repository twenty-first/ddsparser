package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Value extends AstNode {

	private String string;
	private String number;
	
	public Value(Location location, String string, String number) {
		super(location);
		this.string = string;
		this.number = number;
	}

	public String getString() {
		return string;
	}

	public String getNumber() {
		return number;
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitValue(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
