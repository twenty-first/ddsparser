package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class RefField extends AstNode {

	private String name;
	private String file;
	
	public RefField(Location location, String name, String file) {
		super(location);
		this.name = name;
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public String getFile() {
		return file;
	}

	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
