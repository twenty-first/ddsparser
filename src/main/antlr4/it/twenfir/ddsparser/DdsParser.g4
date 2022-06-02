parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( A_SPEC UNIQUE )?
        A_SPEC RECORD IDENTIFIER
        ( A_SPEC field )+ 
        ( A_SPEC key )* 
        EOF ;

field : IDENTIFIER dataType ( A_SPEC? ( text | colhdg | edtwrd ) )* ;

dataType : SIZE TYPE? SIZE? ;

text : TEXT LPAR description RPAR ;

colhdg : COLHDG LPAR description RPAR ;

edtwrd : EDTWRD LPAR description RPAR ;

description : descriptionElement ( PLUS A_SPEC descriptionElement )* ;

descriptionElement : QUOTE ( DESC_START A_SPEC )* DESCRIPTION QUOTE ;

key : KEY IDENTIFIER DESCEND?;
