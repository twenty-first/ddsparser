package it.twenfir.ddsparser.ast;

import java.util.List;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Jfld extends AstNode {

	private List<String> fields;
	
	public Jfld(Location location, List<String> fields) {
		super(location);
		this.fields = fields;
	}

	public List<String> getFields() {
		return fields;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitJfld(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
