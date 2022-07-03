package it.twenfir.ddsparser.ast;

import it.twenfir.antlr.ast.AstVisitor;

public interface DdsVisitor<ValueT> extends AstVisitor<ValueT> {
    ValueT visitAlias(Alias node);
    ValueT visitAltseq(Altseq node);
    ValueT visitCcsid(Ccsid node);
    ValueT visitComp(Comp node);
    ValueT visitDataType(DataType node);
    ValueT visitDds(Dds node);
    ValueT visitDefault(Default node);
    ValueT visitDescription(Description node);
    ValueT visitDescriptionElement(DescriptionElement node);
    ValueT visitEditCode(EditCode node);
    ValueT visitEditWord(EditWord node);
    ValueT visitField(Field node);
    ValueT visitFileName(FileName node);
    ValueT visitFormat(Format node);
    ValueT visitHeading(Heading node);
    ValueT visitJfile(Jfile node);
    ValueT visitJfld(Jfld node);
    ValueT visitJoin(Join node);
    ValueT visitJref(Jref node);
    ValueT visitKey(Key node);
    ValueT visitOmit(Omit node);
    ValueT visitPhysicalFile(PhysicalFile node);
    ValueT visitRef(Ref node);
    ValueT visitRefField(RefField node);
    ValueT visitSelect(Select node);
    ValueT visitSst(Sst node);
    ValueT visitText(Text node);
    ValueT visitValue(Value node);
    ValueT visitValues(Values node);
}
