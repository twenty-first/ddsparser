package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Alias extends AstNode {

	private String alias;

    public Alias(Location location, String alias) {
        super(location);
		this.alias = alias;
    }

	public String getAlias() {
		return alias;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitAlias(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
