package io.github.twentyfirst.ddsparser.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AstNode {
	
    private ParserRuleContext context;
    private List<AstNode> children = new ArrayList<>();

    public AstNode(ParserRuleContext context) {
        this.context = context;
    }

    public ParserRuleContext getContext() {
    	return context;
    }

    protected void addChild(AstNode child) {
    	if ( child != null ) {
        	children.add(child);
    	}
    }
    
    protected <N extends AstNode> void addChildren(Collection<N> children) {
    	if ( children != null ) {
        	this.children.addAll(children);
    	}
    }

    public <ChildT extends AstNode> ChildT getChild(Class<ChildT> clazz) {
    	ChildrenIterator<ChildT> iter = new ChildrenIterator<ChildT>(getChildren(), clazz);
    	return iter.hasNext() ? iter.next() : null;
    }
    
    public Iterator<AstNode> getChildren() {
    	return children.iterator();
    }
    
    public <ChildT extends AstNode> Iterator<ChildT> getChildren(Class<ChildT> clazz) {
    	return new ChildrenIterator<ChildT>(getChildren(), clazz);
    }
    
	public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
