package it.twenfir.ddsparser.ast;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChildrenIterator<NodeT extends AstNode> implements Iterator<NodeT> {

	Iterator<AstNode> iter;
	NodeT current;
	Class<? extends NodeT> clazz;
	
	public ChildrenIterator(Iterator<AstNode> iter, Class<? extends NodeT> clazz) {
		this.iter = iter;
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	private void advance() {
		while ( iter.hasNext() ) {
			if ( current != null ) {
				break;
			}
			AstNode n = iter.next();
			if ( clazz.isInstance(n) ) {
				current = (NodeT) n;
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		advance();
		return current != null;
	}

	@Override
	public NodeT next() {
		advance();
		if ( current == null ) {
			throw new NoSuchElementException();
		}
		NodeT value = current;
		current = null;
		return value;
	}

}
