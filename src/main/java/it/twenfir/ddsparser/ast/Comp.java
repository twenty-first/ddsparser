package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Comp extends AstNode {

	private String relOp;
	private String value;
	
	public Comp(Location location, String relOp, String value) {
		super(location);
		this.relOp = relOp;
		this.value = value;
	}

	public String getRelOp() {
		return relOp;
	}

	public String getValue() {
		return value;
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitComp(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
