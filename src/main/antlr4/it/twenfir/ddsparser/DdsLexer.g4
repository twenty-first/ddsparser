lexer grammar DdsLexer;

tokens {    A_SPEC, COLHDG, DESC_START, DESCRIPTION, EDTWRD, IDENTIFIER, LPAR,
            QUOTE, R_SPEC, REFFLD, RPAR, TEXT, VALUES }

ST_PREFIX : PREFIX_F -> pushMode(Spec), channel(HIDDEN);

mode Spec;

SP_A_SPEC   : A_SPEC_F -> type(A_SPEC), mode(Def);
SP_SPACE    : ' ' -> channel(HIDDEN), mode(Def);
COMMENT     : ANY_F '*' ANY_F* -> channel(HIDDEN);
SP_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Def;

EMPTY_DEF   : '                                      ' -> channel(HIDDEN), mode(Func);
D_SPACE     : ' ' -> channel(HIDDEN);
D_R_SPEC      : R_SPEC_F -> type(R_SPEC) ;
KEY         : 'K' ;
IDENTIFIER  : IDENTIFIER_F -> type(IDENTIFIER), mode(Type);

mode Type;

TY_SPACE        : ' '+ -> channel(HIDDEN);
SIZE            : [0-9]+;
TY_R_SPEC       : R_SPEC_F -> type(R_SPEC) ;
TYPE            : [ALPSTZ];
TY_TEXT         : TEXT_F -> type(TEXT), mode(Text);
TY_COLHDG       : COLHDG_F -> type(COLHDG), mode(Text);
TY_EDTWRD       : EDTWRD_F -> type(EDTWRD), mode(Text);
TY_REFFLD       : REFFLD_F -> type(REFFLD), pushMode(Reffld);
TY_VALUES       : VALUES_F -> type(VALUES), mode(Values);
DESCEND         : 'DESCEND';
FORMAT          : 'FORMAT';
TY_LPAR         : LPAR_F -> type(LPAR);
TY_RPAR         : RPAR_F -> type(RPAR);
TY_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);
TY_EOL          : EOL_F+ -> channel(HIDDEN), popMode;

mode Reffld;

RF_LPAR         : LPAR_F -> type(LPAR);
RF_RPAR         : RPAR_F -> type(RPAR), popMode;
RF_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);

mode Func ;

FN_SPACE    : ' '+ -> channel(HIDDEN);
UNIQUE      : UNIQUE_F ;
REF         : REF_F -> pushMode(Ref);
FN_TEXT     : TEXT_F -> type(TEXT), mode(Text);
FN_COLHDG   : COLHDG_F -> type(COLHDG), mode(Text);
FN_EDTWRD   : EDTWRD_F -> type(EDTWRD), mode(Text);
FN_REFFLD   : REFFLD_F -> type(REFFLD), pushMode(Reffld);
FN_VALUES   : VALUES_F -> type(VALUES), mode(Values);
FN_QUOTE    : QUOTE_F -> type(QUOTE), mode(Desc);
FN_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Ref;

RE_LPAR         : LPAR_F -> type(LPAR);
RE_RPAR         : RPAR_F -> type(RPAR), popMode;
RE_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);

mode Text;

TE_TEXT        : TEXT_F -> type(TEXT);
TE_COLHDG      : COLHDG_F -> type(COLHDG);
TE_EDTWRD      : EDTWRD_F -> type(EDTWRD);
TE_REFFLD      : REFFLD_F -> type(REFFLD), pushMode(Reffld);
TE_VALUES      : VALUES_F -> type(VALUES), mode(Values);
TE_LPAR        : LPAR_F -> type(LPAR);
TE_RPAR        : RPAR_F -> type(RPAR);
TE_SPACE       : ' ' -> channel(HIDDEN);
PLUS           : '+' -> mode(DescCont);
TE_QUOTE       : QUOTE_F -> type(QUOTE), mode(Desc);
TE_EOL         : EOL_F+ -> channel(HIDDEN), popMode;

mode DescCont;

DC_EOL   : EOL_F+ -> channel(HIDDEN), popMode;

mode DescPrf;

DP_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(DescSpec);

mode DescSpec;

DS_A_SPEC  : A_SPEC_F -> type(A_SPEC);
DS_SPACE   : ' '+ -> channel(HIDDEN), mode(Desc);

mode Desc;

DE_DESC_START  : DESC_START_F -> type(DESC_START), mode(DescPrf);
DE_DESCRIPTION : DESCRIPTION_F -> type(DESCRIPTION);
DE_QUOTE       : QUOTE_F -> type(QUOTE), mode(Text);

mode Values;

VA_LPAR     : LPAR_F -> type(LPAR);
VA_RPAR     : RPAR_F -> type(RPAR), mode(Text);
VA_QUOTE    : QUOTE_F -> type(QUOTE), pushMode(Value);
VA_SPACE    : ' '+ -> channel(HIDDEN);

mode Value;
VALUE       : [A-Z0-9 ]+;
VL_QUOTE    : QUOTE_F -> type(QUOTE), popMode;

// Common fragments

fragment LPAR_F        : '(';
fragment RPAR_F        : ')';
fragment ANY_F         : ~[\r\n] ;
fragment EOL_F         : '\r'? '\n' ;
fragment PREFIX_F      : ANY_F ANY_F ANY_F ANY_F ANY_F ;
fragment A_SPEC_F      : 'A' ;
fragment R_SPEC_F      : 'R' ;
fragment COLHDG_F      : 'COLHDG' ;
fragment EDTWRD_F      : 'EDTWRD' ;
fragment REF_F         : 'REF' ;
fragment REFFLD_F      : 'REFFLD' ;
fragment TEXT_F        : 'TEXT' ;
fragment VALUES_F      : 'VALUES' ;
fragment UNIQUE_F      : 'UNIQUE' ;
fragment IDENTIFIER_F  : [A-Z$][A-Z0-9$]* ;
fragment DESCRIPTION_F : ((~[\r\n'])|('\'\''))+ ;
//fragment DESC_START_F  : ((~[\r\n'+-])|('\'\'')|([+-]~[\r\n]))+[+-] EOL_F ;
fragment DESC_START_F  : (~[\r\n'+-])+ [+-] EOL_F;
fragment QUOTE_F       : '\'' ;
