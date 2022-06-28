package it.twenfir.ddsparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.TerminalNode;

import it.twenfir.antlr.ast.AstHelper;
import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.ast.Location;
import it.twenfir.ddsparser.DdsParser.AliasContext;
import it.twenfir.ddsparser.DdsParser.AltseqContext;
import it.twenfir.ddsparser.DdsParser.CcsidContext;
import it.twenfir.ddsparser.DdsParser.CompContext;
import it.twenfir.ddsparser.DdsParser.DataTypeContext;
import it.twenfir.ddsparser.DdsParser.DdsContext;
import it.twenfir.ddsparser.DdsParser.DescriptionContext;
import it.twenfir.ddsparser.DdsParser.DescriptionElementContext;
import it.twenfir.ddsparser.DdsParser.DftContext;
import it.twenfir.ddsparser.DdsParser.EditCodeContext;
import it.twenfir.ddsparser.DdsParser.EditWordContext;
import it.twenfir.ddsparser.DdsParser.FieldContext;
import it.twenfir.ddsparser.DdsParser.FormatContext;
import it.twenfir.ddsparser.DdsParser.HeadingContext;
import it.twenfir.ddsparser.DdsParser.KeyContext;
import it.twenfir.ddsparser.DdsParser.OmitContext;
import it.twenfir.ddsparser.DdsParser.PfileContext;
import it.twenfir.ddsparser.DdsParser.RefContext;
import it.twenfir.ddsparser.DdsParser.RefFieldContext;
import it.twenfir.ddsparser.DdsParser.SelectContext;
import it.twenfir.ddsparser.DdsParser.SstContext;
import it.twenfir.ddsparser.DdsParser.TextContext;
import it.twenfir.ddsparser.DdsParser.ValuesContext;
import it.twenfir.ddsparser.ast.Alias;
import it.twenfir.ddsparser.ast.Altseq;
import it.twenfir.ddsparser.ast.Ccsid;
import it.twenfir.ddsparser.ast.Comp;
import it.twenfir.ddsparser.ast.DataType;
import it.twenfir.ddsparser.ast.Dds;
import it.twenfir.ddsparser.ast.Default;
import it.twenfir.ddsparser.ast.Description;
import it.twenfir.ddsparser.ast.DescriptionElement;
import it.twenfir.ddsparser.ast.EditCode;
import it.twenfir.ddsparser.ast.EditWord;
import it.twenfir.ddsparser.ast.Field;
import it.twenfir.ddsparser.ast.Format;
import it.twenfir.ddsparser.ast.Heading;
import it.twenfir.ddsparser.ast.Key;
import it.twenfir.ddsparser.ast.Omit;
import it.twenfir.ddsparser.ast.Pfile;
import it.twenfir.ddsparser.ast.Ref;
import it.twenfir.ddsparser.ast.RefField;
import it.twenfir.ddsparser.ast.Select;
import it.twenfir.ddsparser.ast.Sst;
import it.twenfir.ddsparser.ast.Text;
import it.twenfir.ddsparser.ast.Values;

public class AstBuilder extends DdsParserBaseVisitor<AstNode> {

	private Pattern endDescRe = Pattern.compile("\\+|-");

	@Override
	public Alias visitAlias(AliasContext ctx) {
		Location location = AstHelper.location(ctx);
		String alias = ctx.IDENTIFIER().getText();
		Alias node = new Alias(location, alias);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Altseq visitAltseq(AltseqContext ctx) {
		Location location = AstHelper.location(ctx);
		String altseq = ctx.IDENTIFIER().getText();
		Altseq node = new Altseq(location, altseq);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Comp visitComp(CompContext ctx) {
		Location location = AstHelper.location(ctx);
		String relOp = ctx.REL_OP().getText();
		String value = ctx.VALUE() != null ? ctx.VALUE().getText() : ctx.NUMBER().getText();
		Comp node = new Comp(location, relOp, value);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Ccsid visitCcsid(CcsidContext ctx) {
		Location location = AstHelper.location(ctx);
		String ccsid = ctx.NUMBER().getText();
		Ccsid node = new Ccsid(location, ccsid);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public DataType visitDataType(DataTypeContext ctx) {
		Location location = AstHelper.location(ctx);
		String type = ctx.TYPE() != null ? ctx.TYPE().getText() : null;
		Integer size = ctx.SIZE(0) != null ? Integer.parseInt(ctx.SIZE(0).getText()) : null;
		Integer precision = ctx.SIZE(1) != null ? Integer.parseInt(ctx.SIZE(1).getText()) : null;
		DataType node = new DataType(location, type, size, precision);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}
	
	@Override
	public Dds visitDds(DdsContext ctx) {
		Location location = AstHelper.location(ctx);
		String record = ctx.record.getText();
		boolean unique = ctx.UNIQUE() != null;
		Dds node = new Dds(location, record, unique);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Default visitDft(DftContext ctx) {
		Location location = AstHelper.location(ctx);
		Default node = new Default(location);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Description visitDescription(DescriptionContext ctx) {
		Location location = AstHelper.location(ctx);
		Description node = new Description(location);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public DescriptionElement visitDescriptionElement(DescriptionElementContext ctx) {
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
		if ( ctx.DESCRIPTION() != null ) {
			sb.append(ctx.DESCRIPTION().getText());
		}
		DescriptionElement node = new DescriptionElement(location, sb.toString());
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public EditCode visitEditCode(EditCodeContext ctx) {
		Location location = AstHelper.location(ctx);
		String editCode = ctx.EDITCODE().getText();
		EditCode node = new EditCode(location, editCode);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public EditWord visitEditWord(EditWordContext ctx) {
		Location location = AstHelper.location(ctx);
		EditWord node = new EditWord(location);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Field visitField(FieldContext ctx) {
		Location location = AstHelper.location(ctx);
		String name = ctx.IDENTIFIER().getText();
		boolean allowNull = ctx.ALWNULL() != null;
		Field node = new Field(location, name, allowNull);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Format visitFormat(FormatContext ctx) {
		Location location = AstHelper.location(ctx);
		String format = ctx.IDENTIFIER().getText();
		Format node = new Format(location, format);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Heading visitHeading(HeadingContext ctx) {
		Location location = AstHelper.location(ctx);
		Heading node = new Heading(location);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Key visitKey(KeyContext ctx) {
		Location location = AstHelper.location(ctx);
		String fieldName = ctx.IDENTIFIER().getText();
		boolean descending = ctx.DESCEND() != null;
		Key node = new Key(location, fieldName, descending);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Omit visitOmit(OmitContext ctx) {
		Location location = AstHelper.location(ctx);
		String fieldName = ctx.IDENTIFIER().getText();
		Omit node = new Omit(location, fieldName);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Pfile visitPfile(PfileContext ctx) {
		Location location = AstHelper.location(ctx);
		String pfile = ctx.IDENTIFIER().getText();
		Pfile node = new Pfile(location, pfile);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Ref visitRef(RefContext ctx) {
		Location location = AstHelper.location(ctx);
		String reference = ctx.ref_file.getText();
		String library = null;
		if ( ctx.ref_lib != null ) {
			library = ctx.ref_lib.getText();
		}
		else if ( ctx.CONSTANT() != null ) {
			library = ctx.CONSTANT().getText();
		}
		Ref node = new Ref(location, library, reference);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public RefField visitRefField(RefFieldContext ctx) {
		Location location = AstHelper.location(ctx);
		String name = ctx.ref_field.getText();
		String library = null;
		if ( ctx.ref_lib != null ) {
			library = ctx.ref_lib.getText();
		}
		else if ( ctx.con_lib != null ) {
			library = ctx.con_lib.getText();
		}
		String file = null;
		if ( ctx.ref_file != null ) {
			file = ctx.ref_file.getText();
		}
		else if ( ctx.con_file != null ) {
			file = ctx.con_file.getText();
		}
		RefField node = new RefField(location, name, library, file);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Select visitSelect(SelectContext ctx) {
		Location location = AstHelper.location(ctx);
		String fieldName = ctx.IDENTIFIER().getText();
		Select node = new Select(location, fieldName);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Sst visitSst(SstContext ctx) {
		Location location = AstHelper.location(ctx);
		String field = ctx.IDENTIFIER().getText();
		Integer from = Integer.decode(ctx.NUMBER(0).getText());
		Integer length = ctx.NUMBER().size() > 1 ? Integer.decode(ctx.NUMBER(1).getText()) : null;
		Sst node = new Sst(location, field, from, length);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Text visitText(TextContext ctx) {
		Location location = AstHelper.location(ctx);
		Text node = new Text(location);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Values visitValues(ValuesContext ctx) {
		Location location = AstHelper.location(ctx);
		List<String> valueList = new ArrayList<>();
		ctx.VALUE().forEach((v) -> { valueList.add(v.getText()); });
		Values node = new Values(location, valueList);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}
}
