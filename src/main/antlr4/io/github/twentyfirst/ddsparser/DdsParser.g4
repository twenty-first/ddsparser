parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( SPACE | EOL )* spec RECORD SPACE IDENTIFIER SPACE?
        ( EOL spec field )+ 
        ( EOL spec key )* 
        ( SPACE | EOL )* EOF ;

field : IDENTIFIER SPACE dataType SPACE TEXT LPAR QUOTE DESCRIPTION QUOTE RPAR SPACE? ;

dataType : SIZE ( TYPE SPACE SIZE )? ;

key : KEY SPACE IDENTIFIER SPACE? ;

spec : PREFIX A_SPEC SPACE? ;
