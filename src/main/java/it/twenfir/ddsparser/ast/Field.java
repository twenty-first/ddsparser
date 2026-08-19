package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.parser.ast.CommonField;

public class Field extends AstNode implements CommonField {

	private String name;
	private String usage;
	private boolean reference;
	private Relative sizeRelative;
	private Relative precRelative;
	private boolean allowNull;
	
	public Field(Location location, String name, String usage, boolean reference, Relative sizeRelative, 
			Relative precRelative, boolean allowNull) {
		super(location);
		this.name = name;
		this.usage = usage;
		this.reference = reference;
		this.sizeRelative = sizeRelative;
		this.precRelative = precRelative;
		this.allowNull = allowNull;
	}
	
    public String getName() {
		return name;
	}

    public String getUsage() {
    	return usage;
    }
    
    public boolean isReference() {
    	return reference;
    }

	public Relative getSizeRelative() {
		return sizeRelative;
	}

	public boolean isSizeRelative() {
		return sizeRelative != null;
	}

	public Relative getPrecRelative() {
		return precRelative;
	}

	public boolean isPrecRelative() {
		return precRelative != null;
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
		DataType dt = getChild(DataType.class);
		if ( dt != null ) {
			Varlen v = getChild(Varlen.class);
			if ( v != null ) {
				dt.setVarsize(true);
				dt.setMinSize(v.getSize());
			}
		}
		return dt;
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
	
	public boolean isVarlen() {
		return getChild(Varlen.class) != null;		
	}
	
	public Integer getVarlenSize() {
		Varlen v = getChild(Varlen.class);
		return v != null ? v.getSize() : null;
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
