package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;


public class Dds extends AstNode {

	private String recordFormat;
	private boolean unique;
	
	public Dds(Location location, String recordFormat, boolean unique) {
		super(location);
		this.recordFormat = recordFormat;
		this.unique = unique;
	}

	public String getRecordFormat() {
		return recordFormat;
	}
	
	public boolean isUnique() {
		return unique;
	}
	
	public Iterator<Field> getFields() {
		return getChildren(Field.class);
	}
	
	public Iterator<Key> getKeys() {
		return getChildren(Key.class);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }
}
