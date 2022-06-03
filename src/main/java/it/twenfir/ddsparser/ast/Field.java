package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
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

	public DataType getDataType() {
		return getChild(DataType.class);
	}

	public Text getText() {
		return getChild(Text.class);
	}

	public Heading getHeading() {
		return getChild(Heading.class);
	}
	
	public Values getValues() {
		return getChild(Values.class);
	}
	
	public RefField getRefField() {
		return getChild(RefField.class);
	}
	
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

}
