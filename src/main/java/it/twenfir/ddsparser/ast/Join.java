package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Join extends AstNode {
	
	public Join(Location location) {
		super(location);
	}

	public Iterator<JoinFile> getFields() {
		return getChildren(JoinFile.class);
	}
	
	public Iterator<Jfld> getJflds() {
		return getChildren(Jfld.class);
	}

    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitJoin(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
