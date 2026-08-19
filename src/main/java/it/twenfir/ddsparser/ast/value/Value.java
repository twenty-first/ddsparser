package it.twenfir.ddsparser.ast.value;

import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.Location;

public abstract class Value extends AstNode {

	public Value(Location location) {
		super(location);
	}

}
