parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   A_SPEC RECORD IDENTIFIER
        ( A_SPEC field )+ 
        ( A_SPEC key )* 
        EOF ;

field : IDENTIFIER dataType ( text | colhdg )+ ;

dataType : SIZE ( TYPE? SIZE )? ;

text : TEXT LPAR QUOTE DESCRIPTION QUOTE RPAR ;

colhdg : COLHDG LPAR QUOTE DESCRIPTION QUOTE RPAR ;

key : KEY IDENTIFIER ;
