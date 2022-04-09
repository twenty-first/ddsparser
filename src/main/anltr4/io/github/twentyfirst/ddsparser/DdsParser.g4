grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds : record field+ key* EOF ;

record : spec RECORD SPACE IDENTIFIER SPACE? EOL ;

field : spec IDENTIFIER SPACE SIZE ( TYPE SPACE SIZE )? TEXT LPAR QUOTE DESCRIPTION QUOTE RPAR eol ;

key : spec KEY SPACE IDENTIFIER eol ;

spec : PREFIX A_SPEC SPACE ;

eol : SPACE? EOL? ;
