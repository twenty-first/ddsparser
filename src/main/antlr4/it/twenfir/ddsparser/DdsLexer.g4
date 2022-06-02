lexer grammar DdsLexer;

tokens { COLHDG, DESC_START, DESCRIPTION, EDTWRD, IDENTIFIER, LPAR, QUOTE, RPAR, TEXT }

ST_PREFIX : PREFIX_F -> pushMode(Spec), channel(HIDDEN);

mode Spec;

SP_A_SPEC   : A_SPEC_F -> channel(HIDDEN), mode(Def);
COMMENT     : ANY_F '*' ANY_F* -> channel(HIDDEN);
SP_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Def;

EMPTY_DEF   : '                                      ' -> channel(HIDDEN), mode(Func);
D_SPACE     : ' ' -> channel(HIDDEN);
RECORD      : 'R' ;
KEY         : 'K' ;
IDENTIFIER  : IDENTIFIER_F -> type(IDENTIFIER), mode(Type);

mode Type;

TY_SPACE        : ' '+ -> channel(HIDDEN);
SIZE            : [0-9]+;
TYPE            : [ALPSTZ];
TY_TEXT         : TEXT_F -> type(TEXT), mode(Text);
TY_COLHDG       : COLHDG_F -> type(COLHDG), mode(Text);
TY_EDTWRD       : EDTWRD_F -> type(EDTWRD), mode(Text);
DESCEND         : 'DESCEND';
FORMAT          : 'FORMAT';
TY_LPAR         : LPAR_F -> type(LPAR);
TY_RPAR         : RPAR_F -> type(RPAR);
TY_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);
TY_EOL          : EOL_F+ -> channel(HIDDEN), popMode;

mode Func ;

FN_SPACE    : ' '+ -> channel(HIDDEN);
UNIQUE      : UNIQUE_F ;
FN_TEXT     : TEXT_F -> type(TEXT), mode(Text);
FN_COLHDG   : COLHDG_F -> type(COLHDG), mode(Text);
FN_EDTWRD   : EDTWRD_F -> type(EDTWRD), mode(Text);
FN_QUOTE    : QUOTE_F -> type(QUOTE), mode(Desc);
FN_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Text;

TE_TEXT        : TEXT_F -> type(TEXT);
TE_COLHDG      : COLHDG_F -> type(COLHDG);
TE_EDTWRD      : EDTWRD_F -> type(EDTWRD);
LPAR           : '(';
RPAR           : ')';
TE_SPACE       : ' ' -> channel(HIDDEN);
PLUS           : '+' -> mode(DescCont);
TE_QUOTE       : QUOTE_F -> type(QUOTE), mode(Desc);
TE_EOL         : EOL_F+ -> channel(HIDDEN), popMode;

mode DescCont;

DC_EOL   : EOL_F+ -> channel(HIDDEN), popMode;

mode DescPrf;

DP_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(DescSpec);

mode DescSpec;

DS_A_SPEC  : A_SPEC_F -> channel(HIDDEN);
DS_SPACE   : ' '+ -> channel(HIDDEN), mode(Desc);

mode Desc;

DE_DESC_START  : DESC_START_F -> type(DESC_START), mode(DescPrf);
DE_DESCRIPTION : DESCRIPTION_F -> type(DESCRIPTION);
DE_QUOTE       : QUOTE_F -> type(QUOTE), mode(Text);

// Common fragments

fragment LPAR_F        : '(';
fragment RPAR_F        : ')';
fragment ANY_F         : ~[\r\n] ;
fragment EOL_F         : '\r'? '\n' ;
fragment PREFIX_F      : ANY_F ANY_F ANY_F ANY_F ANY_F ;
fragment A_SPEC_F      : 'A'|' ' ;
fragment COLHDG_F      : 'COLHDG' ;
fragment EDTWRD_F      : 'EDTWRD' ;
fragment TEXT_F        : 'TEXT' ;
fragment UNIQUE_F      : 'UNIQUE' ;
fragment IDENTIFIER_F  : [A-Z$][A-Z0-9$]* ;
fragment DESCRIPTION_F : ((~[\r\n'])|('\'\''))+ ;
//fragment DESC_START_F  : ((~[\r\n'+-])|('\'\'')|([+-]~[\r\n]))+[+-] EOL_F ;
fragment DESC_START_F  : (~[\r\n'+-])+ [+-] EOL_F;
fragment QUOTE_F       : '\'' ;
