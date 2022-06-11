package it.twenfir.ddsparser.ast;

import java.util.List;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Values extends AstNode {

	private List<String> values;
	
	public Values(Location location, List<String> values) {
		super(location);
		this.values = values;
	}

	public List<String> getValues() {
		return values;
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitValues(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
