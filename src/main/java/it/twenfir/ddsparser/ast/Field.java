package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Field extends AstNode {

	private String name;
	private String ccsid;
	private String editCode;
	private boolean allowNull;
	
	public Field(Location location, String name, String ccsid, String editCode, boolean allowNull) {
		super(location);
		this.name = name;
		this.ccsid = ccsid;
		this.editCode = editCode;
		this.allowNull = allowNull;
	}
	
    public String getName() {
		return name;
	}

	public String getCcsid() {
		return ccsid;
	}

	public String getEditCode() {
		return editCode;
	}

	public boolean isAllowNull() {
		return allowNull;
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
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitField(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
