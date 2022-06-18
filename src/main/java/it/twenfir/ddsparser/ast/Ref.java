package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Ref extends AstNode {

	private String library;
	private String reference;
	
	public Ref(Location location, String library, String reference) {
		super(location);
		this.library = library;
		this.reference = reference;
	}

	public String getLibrary() {
		return library;
	}

	public String getReference() {
		return reference;
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
