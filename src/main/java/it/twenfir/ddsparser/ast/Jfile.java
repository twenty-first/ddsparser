package it.twenfir.ddsparser.ast;

import java.util.List;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Jfile extends AstNode {

	private List<String> files;
	
	public Jfile(Location location, List<String> files) {
		super(location);
		this.files = files;
	}

	public List<String> getFiles() {
		return files;
	}

	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitJfile(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
