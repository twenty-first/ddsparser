package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class EditCode extends AstNode{

	private String editCode;

    public EditCode(Location location, String editCode) {
        super(location);
		this.editCode = editCode;
    }

	public String getEditCode() {
		return editCode;
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitEditCode(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
