package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Description extends AstNode {

	private String description;
	
	public Description(Location location) {
		super(location);
	}
	
	public String getDescription() {
		if ( description == null ) {
			StringBuilder sb = new StringBuilder();
			getDescriptionElements().forEachRemaining((de) -> { sb.append(de.getDescription()); });
			description = sb.toString();
		}
		return description;
	}
	
	public Iterator<DescriptionElement> getDescriptionElements() {
		return getChildren(DescriptionElement.class);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
