package it.twenfir.ddsparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.TerminalNode;

import it.twenfir.antlr.ast.AstHelper;
import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.Location;
import it.twenfir.ddsparser.DdsParser.ColhdgContext;
import it.twenfir.ddsparser.DdsParser.DataTypeContext;
import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.DdsParser.DescriptionContext;
import it.twenfir.ddsparser.DdsParser.DescriptionElementContext;
import it.twenfir.ddsparser.DdsParser.EdtwrdContext;
import it.twenfir.ddsparser.DdsParser.FieldContext;
import it.twenfir.ddsparser.DdsParser.KeyContext;
import it.twenfir.ddsparser.DdsParser.TextContext;
import it.twenfir.ddsparser.ast.DataType;
import it.twenfir.ddsparser.ast.Dds;
import it.twenfir.ddsparser.ast.Description;
import it.twenfir.ddsparser.ast.DescriptionElement;
import it.twenfir.ddsparser.ast.EditWord;
import it.twenfir.ddsparser.ast.Field;
import it.twenfir.ddsparser.ast.Heading;
import it.twenfir.ddsparser.ast.Key;
import it.twenfir.ddsparser.ast.Text;

public class AstBuilder extends DdsParserBaseVisitor<AstNode> {

	private Pattern endDescRe = Pattern.compile("\\+|-");
	
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
		Field field = new Field(location, name);
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
	public AstNode visitColhdg(ColhdgContext ctx) {
		Location location = AstHelper.location(ctx);
		Heading heading = new Heading(location);
		AstHelper.visitChildren(this, ctx, heading);
		return heading;
	}

	@Override
	public AstNode visitEdtwrd(EdtwrdContext ctx) {
		Location location = AstHelper.location(ctx);
		EditWord editWord = new EditWord(location);
		AstHelper.visitChildren(this, ctx, editWord);
		return editWord;
	}

	@Override
	public AstNode visitText(TextContext ctx) {
		Location location = AstHelper.location(ctx);
		Text text = new Text(location);
		AstHelper.visitChildren(this, ctx, text);
		return text;
	}

	@Override
	public AstNode visitDescription(DescriptionContext ctx) {
		Location location = AstHelper.location(ctx);
		Description description = new Description(location);
		AstHelper.visitChildren(this, ctx, description);
		return description;
	}

	@Override
	public AstNode visitDescriptionElement(DescriptionElementContext ctx) {
		Location location = AstHelper.location(ctx);
		StringBuilder sb = new StringBuilder();
		for ( TerminalNode ds : ctx.DESC_START() ) {
			Matcher m = endDescRe.matcher(ds.getText());
			int i = -1;
			while ( m.find() ) {
				i = m.start();
			}
			String s = ds.getText().charAt(i) == '-' && ds.getText().charAt(i-1) == ' ' ?
					ds.getText().substring(0, i - 1) : ds.getText().substring(0, i);
			sb.append(s);
		}
		sb.append(ctx.DESCRIPTION().getText());
		DescriptionElement descriptionElement = new DescriptionElement(location, sb.toString());
		AstHelper.visitChildren(this, ctx, descriptionElement);
		return descriptionElement;
	}

	@Override
	public Key visitKey(KeyContext ctx) {
		Location location = AstHelper.location(ctx);
		String fieldName = ctx.IDENTIFIER().getText();
		boolean descending = ctx.DESCEND() != null;
		Key key = new Key(location, fieldName, descending);
		AstHelper.visitChildren(this, ctx, key);
		return key;
	}
}
