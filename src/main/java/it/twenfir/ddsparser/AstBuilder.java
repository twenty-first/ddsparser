package it.twenfir.ddsparser;

import java.util.ArrayList;
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
import it.twenfir.ddsparser.DdsParser.FileNameContext;
import it.twenfir.ddsparser.DdsParser.FormatContext;
import it.twenfir.ddsparser.DdsParser.HeadingContext;
import it.twenfir.ddsparser.DdsParser.JfileContext;
import it.twenfir.ddsparser.DdsParser.JfldContext;
import it.twenfir.ddsparser.DdsParser.JoinContext;
import it.twenfir.ddsparser.DdsParser.JrefContext;
import it.twenfir.ddsparser.DdsParser.KeyContext;
import it.twenfir.ddsparser.DdsParser.OmitContext;
import it.twenfir.ddsparser.DdsParser.PhysicalFileContext;
import it.twenfir.ddsparser.DdsParser.RefContext;
import it.twenfir.ddsparser.DdsParser.RefFieldContext;
import it.twenfir.ddsparser.DdsParser.SelectContext;
import it.twenfir.ddsparser.DdsParser.SstContext;
import it.twenfir.ddsparser.DdsParser.TextContext;
import it.twenfir.ddsparser.DdsParser.ValueContext;
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
import it.twenfir.ddsparser.ast.FileName;
import it.twenfir.ddsparser.ast.Format;
import it.twenfir.ddsparser.ast.Heading;
import it.twenfir.ddsparser.ast.Jfile;
import it.twenfir.ddsparser.ast.Jfld;
import it.twenfir.ddsparser.ast.Join;
import it.twenfir.ddsparser.ast.Jref;
import it.twenfir.ddsparser.ast.Key;
import it.twenfir.ddsparser.ast.Omit;
import it.twenfir.ddsparser.ast.PhysicalFile;
import it.twenfir.ddsparser.ast.Ref;
import it.twenfir.ddsparser.ast.RefField;
import it.twenfir.ddsparser.ast.Select;
import it.twenfir.ddsparser.ast.Sst;
import it.twenfir.ddsparser.ast.Text;
import it.twenfir.ddsparser.ast.Value;
import it.twenfir.ddsparser.ast.Values;

public class AstBuilder extends DdsParserBaseVisitor<AstNode> {

	private Pattern endDescRe = Pattern.compile("\\+|-");
	private Pattern eolRe = Pattern.compile("\\r|\\n");

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
		String value = ctx.STRING() != null ? ctx.STRING().getText() : ctx.NUMBER().getText();
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
		Integer size = ctx.NUMBER(0) != null ? Integer.parseInt(ctx.NUMBER(0).getText()) : null;
		Integer precision = ctx.NUMBER(1) != null ? Integer.parseInt(ctx.NUMBER(1).getText()) : null;
		if ( precision == null && type != null && type.charAt(0) == 'S' ) {
			precision = 0;
		}
		DataType node = new DataType(location, type, size, precision);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}
	
	@Override
	public Dds visitDds(DdsContext ctx) {
		Location location = AstHelper.location(ctx);
		String record = ctx.record.getText();
		boolean fileJoin = ctx.JDFTVAL().size() > 0;
		boolean unique = ctx.UNIQUE() != null;
		Dds node = new Dds(location, record, fileJoin, unique);
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
		for ( TerminalNode ds : ctx.STRING_START() ) {
			Matcher m = endDescRe.matcher(ds.getText());
			int i = -1;
			while ( m.find() ) {
				i = m.start();
			}
			if ( i != -1 ) {
				String s = ds.getText().charAt(i) == '-' && ds.getText().charAt(i-1) == ' ' ?
						ds.getText().substring(0, i - 1) : ds.getText().substring(0, i);
				sb.append(s);
			}
			else {
				m = eolRe.matcher(ds.getText());
				m.find();
				i = m.start();
				sb.append(ds.getText().substring(0, i));
			}
		}
		if ( ctx.STRING() != null ) {
			sb.append(ctx.STRING().getText());
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
		String usage = ctx.USAGE() != null ? ctx.USAGE().getText() : null;
		boolean reference = ctx.REFERENCE() != null;
		boolean allowNull = ctx.ALWNULL() != null;
		Field node = new Field(location, name, usage, reference, allowNull);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public FileName visitFileName(FileNameContext ctx) {
		Location location = AstHelper.location(ctx);
		String lib = ctx.lib != null ? ctx.lib.getText() : null;
		String name = ctx.name != null ? ctx.name.getText() : null;
		FileName node = new FileName(location, lib, name);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Format visitFormat(FormatContext ctx) {
		Location location = AstHelper.location(ctx);
		Format node = new Format(location);
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
	public Jfile visitJfile(JfileContext ctx) {
		Location location = AstHelper.location(ctx);
		ArrayList<String> files = new ArrayList<String>();
		ctx.IDENTIFIER().forEach((f) -> files.add(f.getText()));
		Jfile node = new Jfile(location, files);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Jfld visitJfld(JfldContext ctx) {
		Location location = AstHelper.location(ctx);
		ArrayList<String> fields = new ArrayList<String>();
		ctx.IDENTIFIER().forEach((f) -> fields.add(f.getText()));
		Jfld node = new Jfld(location, fields);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Join visitJoin(JoinContext ctx) {
		Location location = AstHelper.location(ctx);
		ArrayList<String> files = new ArrayList<String>();
		ctx.IDENTIFIER().forEach((f) -> files.add(f.getText()));
		Join node = new Join(location, files);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Jref visitJref(JrefContext ctx) {
		Location location = AstHelper.location(ctx);
		int index = Integer.parseInt(ctx.NUMBER().getText());
		Jref node = new Jref(location, index);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Key visitKey(KeyContext ctx) {
		Location location = AstHelper.location(ctx);
		String fieldName = ctx.IDENTIFIER().getText();
		boolean descending = ! ctx.DESCEND().isEmpty();
		Key node = new Key(location, fieldName, descending);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Omit visitOmit(OmitContext ctx) {
		Location location = AstHelper.location(ctx);
		boolean all = ctx.ALL() != null;
		String fieldName = null;
		if ( ! all ) {
			fieldName = ctx.IDENTIFIER().getText();
		}
		Omit node = new Omit(location, fieldName, all);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public PhysicalFile visitPhysicalFile(PhysicalFileContext ctx) {
		Location location = AstHelper.location(ctx);
		PhysicalFile node = new PhysicalFile(location);
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
		boolean all = ctx.ALL() != null;
		String fieldName = null;
		if ( ! all ) {
			fieldName = ctx.IDENTIFIER().getText();
		}
		Select node = new Select(location, fieldName, all);
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
	public Value visitValue(ValueContext ctx) {
		Location location = AstHelper.location(ctx);
		String string = ctx.STRING() != null ? ctx.STRING().getText() : null;
		String number = ctx.NUMBER() != null ? ctx.NUMBER().getText() : null;
		Value node = new Value(location, string, number);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}

	@Override
	public Values visitValues(ValuesContext ctx) {
		Location location = AstHelper.location(ctx);
		Values node = new Values(location);
		AstHelper.visitChildren(this, ctx, node);
		return node;
	}
}
