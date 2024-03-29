package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;

public class DataType extends AstNode {

	private String type;
	private Integer size;
	private Integer precision;
	private boolean varsize;
	private Integer minSize;
	
	public DataType(Location location, String type, Integer size, Integer precision) {
		super(location);
		this.type = type;
		this.size = size;
		this.precision = precision;
	}
	
    public String getType() {
		return type;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getPrecision() {
		return precision;
	}

    public boolean isVarsize() {
		return varsize;
	}

	public void setVarsize(boolean varsize) {
		this.varsize = varsize;
	}

	public Integer getMinSize() {
		return minSize;
	}

	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}

	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitDataType(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }

}
