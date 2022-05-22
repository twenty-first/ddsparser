package it.twenfir.ddsparser;

import it.twenfir.antlr.ast.AstHelper;
import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.Location;
import it.twenfir.ddsparser.DdsParser.DataTypeContext;
import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.DdsParser.FieldContext;
import it.twenfir.ddsparser.DdsParser.KeyContext;
import it.twenfir.ddsparser.ast.DataType;
import it.twenfir.ddsparser.ast.Dds;
import it.twenfir.ddsparser.ast.Field;
import it.twenfir.ddsparser.ast.Key;

public class AstBuilder extends DdsParserBaseVisitor<AstNode> {

	@Override
	public Dds visitDds(DdsContext ctx) {
		Location location = AstHelper.location(ctx);
		String recordFormat = ctx.IDENTIFIER().getText();
		boolean unique = ctx.UNIQUE() != null;
		Dds dds = new Dds(location, recordFormat, unique);
		AstHelper.visitChildren(this, ctx, dds);
		return dds;
	}

	@Override
	public Field visitField(FieldContext ctx) {
		Location location = AstHelper.location(ctx);
		String name = ctx.IDENTIFIER().getText();
		String heading = ctx.colhdg().size() > 0 ? ctx.colhdg(0).DESCRIPTION().getText() : null;
		String description = ctx.text().size() > 0 ? ctx.text(0).DESCRIPTION().getText() : null;
		Field field = new Field(location, name, heading, description);
		AstHelper.visitChildren(this, ctx, field);
		return field;
	}

	@Override
	public DataType visitDataType(DataTypeContext ctx) {
		Location location = AstHelper.location(ctx);
		String type = ctx.TYPE() != null ? ctx.TYPE().getText() : null;
		Integer size = Integer.parseInt(ctx.SIZE(0).getText());
		Integer precision = ctx.SIZE(1) != null ? Integer.parseInt(ctx.SIZE(1).getText())  : null;
		DataType dataType = new DataType(location, type, size, precision);
		AstHelper.visitChildren(this, ctx, dataType);
		return dataType;
	}

	@Override
	public Key visitKey(KeyContext ctx) {
		Location location = AstHelper.location(ctx);
		String fieldName = ctx.IDENTIFIER().getText();
		Key key = new Key(location, fieldName);
		AstHelper.visitChildren(this, ctx, key);
		return key;
	}
}
