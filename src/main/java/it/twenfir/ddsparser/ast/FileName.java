package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class FileName extends AstNode {

	private String library;
	private String name;
	
	public FileName(Location location, String library, String name) {
		super(location);
		this.library = library;
		this.name = name;
	}

	public String getLibrary() {
		return library;
	}

	public String getName() {
		return name;
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitFileName(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
