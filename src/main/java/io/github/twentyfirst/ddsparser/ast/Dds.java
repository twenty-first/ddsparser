package io.github.twentyfirst.ddsparser.ast;

import java.util.Iterator;

import io.github.twentyfirst.ddsparser.DdsParser.DdsContext;

public class Dds extends AstNode {

	private String recordFormat;
	
	public Dds(DdsContext context) {
		super(context);
		recordFormat = context.IDENTIFIER().getText();
	}

	public String getRecordFormat() {
		return recordFormat;
	}

	public void addField(Field f) {
		addChild(f);
	}
	
	public Iterator<Field> getFields() {
		return getChildren(Field.class);
	}
	
	public void addKey(Key k) {
		addChild(k);
	}
	
	public Iterator<Key> getKeys() {
		return getChildren(Key.class);
	}
	
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }
}
