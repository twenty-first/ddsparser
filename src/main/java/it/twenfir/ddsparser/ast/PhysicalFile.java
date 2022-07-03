package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class PhysicalFile extends AstNode {

    public PhysicalFile(Location location) {
        super(location);
    }
	
	public Iterator<FileName> getFileNames() {
		return getChildren(FileName.class);
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitPhysicalFile(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
    
}
