package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.ChildrenIterator;
import it.twenfir.antlr.ast.Location;

public class Field extends AstNode {

	private String name;
	
	public Field(Location location, String name) {
		super(location);
		this.name = name;
	}
	
    public String getName() {
		return name;
	}

	public Text getText() {
		Iterator<Text> iter = new ChildrenIterator<Text>(getChildren(), Text.class);
		return iter.hasNext() ? iter.next() : null;
	}

	public Heading getHeading() {
		Iterator<Heading> iter = new ChildrenIterator<Heading>(getChildren(), Heading.class);
		return iter.hasNext() ? iter.next() : null;
	}
	
	public DataType getDataType() {
		return new ChildrenIterator<DataType>(getChildren(), DataType.class).next();
	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
