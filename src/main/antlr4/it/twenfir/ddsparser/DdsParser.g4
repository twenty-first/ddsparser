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

field : IDENTIFIER dataType ( A_SPEC? ( text | colhdg ) )* ;

dataType : SIZE TYPE? SIZE? ;

text : TEXT LPAR QUOTE description QUOTE RPAR ;

colhdg : COLHDG LPAR QUOTE description QUOTE RPAR ;

description : ( DESC_START A_SPEC )* DESCRIPTION ;

key : KEY IDENTIFIER ;
