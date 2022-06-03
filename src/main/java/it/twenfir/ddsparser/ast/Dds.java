package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;


public class Dds extends AstNode {

	private String record;
	private String format;
	private String reference;
	private boolean unique;
	
	public Dds(Location location, String record, String format, String reference, boolean unique) {
		super(location);
		this.record = record;
		this.format = format;
		this.reference = reference;
		this.unique = unique;
	}

	public String getRecord() {
		return record;
	}
	
	public String getFormat() {
		return format;
	}

	public String getReference() {
		return reference;
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
