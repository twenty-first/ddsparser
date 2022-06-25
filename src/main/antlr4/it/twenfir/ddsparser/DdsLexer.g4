lexer grammar DdsLexer;

tokens { A_SPEC, ALIAS, ALWNULL, CCSID, COLHDG, CONSTANT, DESC_START, DESCRIPTION, DFT, EDTCDE,
         EDTWRD, IDENTIFIER, LPAR, QUOTE, R_SPEC, REFFLD, RPAR, SLASH, TEXT, VALUES }

PREFIX      : PREFIX_F -> channel(HIDDEN), pushMode(Spec);
PART_PREF   : ( ANY_F
              | ( ANY_F ANY_F )
              | ( ANY_F ANY_F ANY_F )
              | ( ANY_F ANY_F ANY_F ANY_F ) 
              ) 
              EOL_F -> channel(HIDDEN)
              ;
              
ST_EOL      : EOL_F+ -> channel(HIDDEN);

mode Spec;

SP_A_SPEC   : A_SPEC_F -> type(A_SPEC), mode(Def);
SP_SPACE    : ' ' -> channel(HIDDEN), mode(Def);
COMMENT     : ANY_F? '*' ANY_F* -> channel(HIDDEN);
SP_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Def;

EMPTY_DEF   : '                                      ' -> channel(HIDDEN), mode(Func);
D_SPACE     : ' ' -> channel(HIDDEN);
D_R_SPEC    : R_SPEC_F -> type(R_SPEC) ;
KEY         : 'K' ;
D_NAME      : NAME_F -> type(IDENTIFIER), mode(Type);
D_EOL       : EOL_F+ -> channel(HIDDEN), popMode;

mode Type;

TY_SPACE        : ' '+ -> channel(HIDDEN);
SIZE            : [0-9]+;
TY_R_SPEC       : R_SPEC_F -> type(R_SPEC) ;
TYPE            : [ABLPSTZ];
TY_ALIAS        : ALIAS_F -> type(ALIAS), mode(Ident);
TY_ALWNULL      : ALWNULL_F -> type(ALWNULL);
TY_COLHDG       : COLHDG_F -> type(COLHDG), mode(Text);
TY_CCSID        : CCSID_F -> type(CCSID), mode(Ccsid);
TY_EDTCDE       : EDTCDE_F -> type(EDTCDE), mode(Edtcde);
TY_EDTWRD       : EDTWRD_F -> type(EDTWRD), mode(Text);
TY_REFFLD       : REFFLD_F -> type(REFFLD), pushMode(Reffld);
TY_TEXT         : TEXT_F -> type(TEXT), mode(Text);
TY_DFT          : DFT_F -> type(DFT), mode(Text);
TY_VALUES       : VALUES_F -> type(VALUES), mode(Values);
DESCEND         : 'DESCEND';
FORMAT          : 'FORMAT' -> mode(Ident);
TY_EOL          : EOL_F+ -> channel(HIDDEN), popMode;

mode Reffld;

RF_LPAR         : LPAR_F -> type(LPAR);
RF_RPAR         : RPAR_F -> type(RPAR), popMode;
RF_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);
RF_CONSTANT     : CONSTANT_F -> type(CONSTANT);
RF_SPACE        : ' '+ -> channel(HIDDEN);
RF_SLASH        : SLASH_F -> type(SLASH);

mode Ident;

FM_LPAR         : LPAR_F -> type(LPAR);
FM_RPAR         : RPAR_F -> type(RPAR), mode(Text);
FM_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);

mode Func ;

FN_SPACE    : ' '+ -> channel(HIDDEN);
UNIQUE      : UNIQUE_F ;
REF         : REF_F -> pushMode(Ref);
FN_ALIAS    : ALIAS_F -> type(ALIAS), mode(Ident);
FN_ALWNULL  : ALWNULL_F -> type(ALWNULL);
FN_COLHDG   : COLHDG_F -> type(COLHDG), mode(Text);
FN_CCSID    : CCSID_F -> type(CCSID), mode(Ccsid);
FN_EDTCDE   : EDTCDE_F -> type(EDTCDE), mode(Edtcde);
FN_EDTWRD   : EDTWRD_F -> type(EDTWRD), mode(Text);
FN_REFFLD   : REFFLD_F -> type(REFFLD), pushMode(Reffld);
FN_TEXT     : TEXT_F -> type(TEXT), mode(Text);
FN_DFT      : DFT_F -> type(DFT), mode(Text);
FN_VALUES   : VALUES_F -> type(VALUES), mode(Values);
FN_QUOTE    : QUOTE_F -> type(QUOTE), mode(Desc);
FN_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Ref;

RE_LPAR         : LPAR_F -> type(LPAR);
RE_RPAR         : RPAR_F -> type(RPAR), popMode;
RE_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);
RE_CONSTANT     : CONSTANT_F -> type(CONSTANT);
RE_SLASH        : SLASH_F -> type(SLASH);

mode Text;

TE_ALIAS       : ALIAS_F -> type(ALIAS), mode(Ident);
TE_ALWNULL     : ALWNULL_F -> type(ALWNULL);
TE_COLHDG      : COLHDG_F -> type(COLHDG);
TE_CCSID       : CCSID_F -> type(CCSID), mode(Ccsid);
TE_EDTCDE      : EDTCDE_F -> type(EDTCDE), mode(Edtcde);
TE_EDTWRD      : EDTWRD_F -> type(EDTWRD);
TE_REFFLD      : REFFLD_F -> type(REFFLD), pushMode(Reffld);
TE_TEXT        : TEXT_F -> type(TEXT);
TE_DFT         : DFT_F -> type(DFT);
TE_VALUES      : VALUES_F -> type(VALUES), mode(Values);
TE_LPAR        : LPAR_F -> type(LPAR);
TE_RPAR        : RPAR_F -> type(RPAR);
TE_SPACE       : ' ' -> channel(HIDDEN);
MINUS          : '-' -> mode(DescCont);
PLUS           : '+' -> mode(DescCont);
TE_QUOTE       : QUOTE_F -> type(QUOTE), mode(Desc);
TE_EOL         : EOL_F+ -> channel(HIDDEN), popMode;

mode Ccsid;

CC_LPAR     : LPAR_F -> type(LPAR);
CC_RPAR     : RPAR_F -> type(RPAR), mode(Text);
NUMBER      : [0-9]+;

mode Edtcde;

EC_LPAR     : LPAR_F -> type(LPAR);
EC_RPAR     : RPAR_F -> type(RPAR), mode(Text);
EDITCODE    : [KZ];

mode DescCont;

DC_EOL   : EOL_F+ -> channel(HIDDEN), popMode;

mode DescPrfMinus;

DPM_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(DescSpecMinus);

mode DescSpecMinus;

DSM_A_SPEC  : A_SPEC_F -> type(A_SPEC);
DSM_SPACE   : '                                      ' -> channel(HIDDEN), mode(Desc);

mode DescPrfPlus;

DPP_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(DescSpecPlus);

mode DescSpecPlus;

DSP_A_SPEC  : A_SPEC_F -> type(A_SPEC);
DSP_SPACE   : ' '+ -> channel(HIDDEN), mode(Desc);

mode Desc;

DE_DESC_START_MINUS : DESC_START_MINUS_F -> type(DESC_START), mode(DescPrfMinus);
DE_DESC_START_PLUS  : DESC_START_PLUS_F -> type(DESC_START), mode(DescPrfPlus);
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

fragment LPAR_F             : '(' ;
fragment RPAR_F             : ')' ;
fragment SLASH_F            : '/' ;
fragment ANY_F              : ~[\r\n] ;
fragment EOL_F              : '\r'? '\n' ;
fragment PREFIX_F           : ANY_F ANY_F ANY_F ANY_F ANY_F ;
fragment A_SPEC_F           : 'A' ;
fragment R_SPEC_F           : 'R' ;
fragment ALIAS_F            : 'ALIAS' ;
fragment ALWNULL_F          : 'ALWNULL' ;
fragment COLHDG_F           : 'COLHDG' ;
fragment CCSID_F            : 'CCSID' ;
fragment DFT_F              : 'DFT' ;
fragment EDTCDE_F           : 'EDTCDE' ;
fragment EDTWRD_F           : 'EDTWRD' ;
fragment REF_F              : 'REF' ;
fragment REFFLD_F           : 'REFFLD' ;
fragment TEXT_F             : 'TEXT' ;
fragment VALUES_F           : 'VALUES' ;
fragment UNIQUE_F           : 'UNIQUE' ;
fragment IDS_F              : [A-Z$\u00a3\u00a7] ;
fragment IDC_F              : [A-Z0-9$_\u00a3\u00a7] ;
fragment NAME_F             : ( IDS_F
                              | ( IDS_F IDC_F )
                              | ( IDS_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F )
                              | ( IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F )
                              )
                              ;                            
fragment IDENTIFIER_F       : IDS_F IDC_F* ;
fragment CONSTANT_F         : '*'[A-Z][A-Z0-9_]* ;
fragment DESCRIPTION_F      : ((~[\r\n'])|('\'\''))+ ;
fragment DESC_START_F       : (('\'\'')|([+-]~[\r\n])|(~[\r\n'+-]))+ ;
fragment DESC_START_PLUS_F  : DESC_START_F '+' EOL_F;
fragment DESC_START_MINUS_F : DESC_START_F '-' EOL_F;
fragment QUOTE_F            : '\'' ;
