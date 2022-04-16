parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   A_SPEC RECORD IDENTIFIER
        ( A_SPEC field )+ 
        ( A_SPEC key )* 
        EOF ;

field : IDENTIFIER dataType TEXT LPAR QUOTE DESCRIPTION QUOTE RPAR;

dataType : SIZE ( TYPE? SIZE )? ;

key : KEY IDENTIFIER ;
