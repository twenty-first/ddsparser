package it.twenfir.ddsparser;

import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.DdsParser.FieldContext;
import it.twenfir.ddsparser.DdsParser.KeyContext;
import it.twenfir.ddsparser.ast.Dds;
import it.twenfir.ddsparser.ast.Field;
import it.twenfir.ddsparser.ast.Key;

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
