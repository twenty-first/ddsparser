package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class Field extends AstNode {

	private String name;
	private String usage;
	private boolean allowNull;
	
	public Field(Location location, String name, String usage, boolean allowNull) {
		super(location);
		this.name = name;
		this.allowNull = allowNull;
	}
	
    public String getName() {
		return name;
	}

    public String getUsage() {
    	return usage;
    }
    
	public boolean isAllowNull() {
		return allowNull;
	}

	public Alias getAlias() {
		return getChild(Alias.class);
	}

	public Ccsid getCcsid() {
		return getChild(Ccsid.class);
	}

	public DataType getDataType() {
		return getChild(DataType.class);
	}

	public Default getDefault() {
		return getChild(Default.class);
	}

	public EditCode getEditCode() {
		return getChild(EditCode.class);
	}
	
	public Heading getHeading() {
		return getChild(Heading.class);
	}
	
	public Iterator<Jref> getJrefs() {
		return getChildren(Jref.class);
	}
	
	public RefField getRefField() {
		return getChild(RefField.class);
	}

	public Sst getSst() {
		return getChild(Sst.class);
	}

	public Text getText() {
		return getChild(Text.class);
	}
	
	public Values getValues() {
		return getChild(Values.class);
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
