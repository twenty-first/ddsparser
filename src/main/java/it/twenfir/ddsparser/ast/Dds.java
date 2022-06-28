package it.twenfir.ddsparser.ast;

import java.util.Iterator;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.AstVisitor;
import it.twenfir.antlr.ast.Location;


public class Dds extends AstNode {

	private String record;
	private boolean unique;
	
	public Dds(Location location, String record, boolean unique) {
		super(location);
		this.record = record;
		this.unique = unique;
	}

	public String getRecord() {
		return record;
	}
	
	public Format getFormat() {
		return getChild(Format.class);
	}

	public boolean isUnique() {
		return unique;
	}
	
	public Altseq getAltseq() {
		return getChild(Altseq.class);
	}
	
	public Pfile getPfile() {
		return getChild(Pfile.class);
	}

	public boolean isLogical() {
		return getPfile() != null;
	}
	
	public Ref getRef() {
		return getChild(Ref.class);
	}
	
	public Text getText() {
		return getChild(Text.class);
	}
	
	public Iterator<Field> getFields() {
		return getChildren(Field.class);
	}
	
	public Iterator<Key> getKeys() {
		return getChildren(Key.class);
	}
	
	public Iterator<Omit> getOmits() {
		return getChildren(Omit.class);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
		if ( visitor instanceof DdsVisitor ) {
			return ((DdsVisitor<? extends ValueT>) visitor).visitDds(this);
    	}
    	else {
    		return visitor.visit(this);
    	}
    }
}
