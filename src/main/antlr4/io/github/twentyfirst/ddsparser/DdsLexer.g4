lexer grammar DdsLexer;

tokens { COLHDG, TEXT }

PREFIX      : ANY_F ANY_F ANY_F ANY_F ANY_F -> pushMode(Spec), channel(HIDDEN);

mode Spec;

A_SPEC      : 'A' -> mode(Def);
COMMENT     : ANY_F '*' ANY_F* -> channel(HIDDEN);
SP_EOL      : EOL_F -> channel(HIDDEN), popMode;

mode Def;

D_SPACE     : ' '+ -> channel(HIDDEN);
RECORD      : 'R' ;
KEY         : 'K' ;
IDENTIFIER  : [A-Z0-9$]+ -> mode(Type);

mode Type;

TY_SPACE    : ' '+ -> channel(HIDDEN);
SIZE        : [0-9]+;
TYPE        : [ALPSTZ];
TY_TEXT     : TEXT_F -> type(TEXT), mode(Text);
TY_COLHDG   : COLHDG_F -> type(COLHDG), mode(Text);
TY_EOL      : EOL_F -> channel(HIDDEN), popMode;

mode Text;

LPAR        : '(';
RPAR        : ')';
QUOTE       : '\'';
DESCRIPTION : ~[\r\n']+;
TE_TEXT     : TEXT_F -> type(TEXT);
TE_COLHDG   : COLHDG_F -> type(COLHDG);
TE_EOL      : EOL_F -> channel(HIDDEN), popMode;

// Common fragments

fragment ANY_F      : ~[\r\n] ;
fragment EOL_F      : '\r'? '\n' ;
fragment TEXT_F     : 'TEXT' ;
fragment COLHDG_F   : 'COLHDG' ;
