parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( SPACE | EOL | comment )* spec RECORD SPACE IDENTIFIER SPACE?
        ( ( EOL | comment )+ spec field )+ 
        ( ( EOL | comment )+ spec key )* 
        ( SPACE | EOL | comment )* EOF ;

field : IDENTIFIER SPACE dataType SPACE TEXT LPAR QUOTE DESCRIPTION QUOTE RPAR SPACE? ;

dataType : SIZE ( TYPE SPACE SIZE )? ;

key : KEY SPACE IDENTIFIER SPACE? ;

spec : PREFIX A_SPEC SPACE? ;

comment : PREFIX COMMENT ;
