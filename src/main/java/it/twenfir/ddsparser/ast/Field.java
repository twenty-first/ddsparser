package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.ChildrenIterator;
import it.twenfir.antlr.ast.Location;

public class Field extends AstNode {

	private String name;
	private String heading;
	private String description;
	
	public Field(Location location, String name, String heading, String description) {
		super(location);
		this.name = name;
		this.heading = heading;
		this.description = description;
	}
	
    public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getHeading() {
		return heading;
	}
	
	public DataType getDataType() {
		return new ChildrenIterator<DataType>(getChildren(), DataType.class).next();
	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
