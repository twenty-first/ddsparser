lexer grammar DdsLexer;

tokens { A_SPEC, COLHDG, DESC_START, DESCRIPTION, TEXT }

ST_PREFIX : PREFIX_F -> pushMode(Spec), channel(HIDDEN);

mode Spec;

SP_A_SPEC   : A_SPEC_F -> type(A_SPEC), mode(Def);
COMMENT     : ANY_F '*' ANY_F* -> channel(HIDDEN);
SP_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Def;

EMPTY_DEF   : '                                      ' -> channel(HIDDEN), mode(Func);
D_SPACE     : ' ' -> channel(HIDDEN);
RECORD      : 'R' ;
KEY         : 'K' ;
IDENTIFIER  : [A-Z0-9$]+ -> mode(Type);

mode Type;

TY_SPACE    : ' '+ -> channel(HIDDEN);
SIZE        : [0-9]+;
TYPE        : [ALPSTZ];
TY_TEXT     : TEXT_F -> type(TEXT), mode(Text);
TY_COLHDG   : COLHDG_F -> type(COLHDG), mode(Text);
DESCEND     : 'DESCEND';
TY_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Func ;

FN_SPACE    : ' '+ -> channel(HIDDEN);
UNIQUE      : UNIQUE_F ;
FN_TEXT     : TEXT_F -> type(TEXT), mode(Text);
FN_COLHDG   : COLHDG_F -> type(COLHDG), mode(Text);
FN_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Text;

LPAR           : '(';
RPAR           : ')';
QUOTE          : '\'';
TE_DESC_START  : DESC_START_F -> type(DESC_START), pushMode(DescPrf);
TE_DESCRIPTION : DESCRIPTION_F -> type(DESCRIPTION);
TE_TEXT        : TEXT_F -> type(TEXT);
TE_COLHDG      : COLHDG_F -> type(COLHDG);
TE_EOL         : EOL_F+ -> channel(HIDDEN), popMode;

mode DescPrf;

DP_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(DescSpec);

mode DescSpec;

DS_A_SPEC  : A_SPEC_F -> type(A_SPEC);
DS_SPACE   : ' '+ -> channel(HIDDEN), mode(Desc);

mode Desc;

DC_DESC_START  : DESC_START_F -> type(DESC_START), mode(DescPrf);
DC_DESCRIPTION : DESCRIPTION_F -> type(DESCRIPTION), popMode;

// Common fragments

fragment ANY_F         : ~[\r\n] ;
fragment EOL_F         : '\r'? '\n' ;
fragment PREFIX_F      : ANY_F ANY_F ANY_F ANY_F ANY_F ;
fragment A_SPEC_F      : 'A' ;
fragment TEXT_F        : 'TEXT' ;
fragment COLHDG_F      : 'COLHDG' ;
fragment UNIQUE_F      : 'UNIQUE' ;
fragment DESCRIPTION_F : ((~[\r\n'])|('\'\''))+ ;
//fragment DESC_START_F  : ((~[\r\n'+-])|('\'\'')|([+-]~[\r\n]))+[+-] EOL_F ;
fragment DESC_START_F  : (~[\r\n'+-])+ [+-] EOL_F;
