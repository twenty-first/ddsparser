package io.github.twentyfirst.ddsparser;

import io.github.twentyfirst.ddsparser.DdsParser.DdsContext;
import io.github.twentyfirst.ddsparser.DdsParser.FieldContext;
import io.github.twentyfirst.ddsparser.DdsParser.KeyContext;
import io.github.twentyfirst.ddsparser.ast.Dds;
import io.github.twentyfirst.ddsparser.ast.Field;
import io.github.twentyfirst.ddsparser.ast.Key;

public class AstBuilder extends DdsParserBaseListener {

	private Dds dds;
	
	public AstBuilder() {
	}

	@Override
	public void enterDds(DdsContext ctx) {
		dds = new Dds(ctx);
	}

	@Override
	public void enterField(FieldContext ctx) {
		dds.addField(new Field(ctx));
	}

	@Override
	public void enterKey(KeyContext ctx) {
		dds.addKey(new Key(ctx));
	}

	public Dds getDds() {
		return dds;
	}
}
