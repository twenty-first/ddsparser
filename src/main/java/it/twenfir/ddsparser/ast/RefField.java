package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class RefField extends AstNode {

	private String name;
	private String library;
	private String file;
	
	public RefField(Location location, String name, String library, String file) {
		super(location);
		this.name = name;
		this.library = library;
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public String getLibrary() {
		return library;
	}

	public String getFile() {
		return file;
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
