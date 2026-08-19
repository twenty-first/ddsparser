package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;
import it.twenfir.parser.ast.CommonDataType;

public class DataType extends CommonDataType {
	
	public DataType(Location location, String type, Integer size, Integer precision) {
		super(location, type, size, precision);
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
