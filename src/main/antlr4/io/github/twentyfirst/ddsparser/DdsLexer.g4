lexer grammar DdsLexer;

tokens { EOL, SPACE }

PREFIX      : ANY_F ANY_F ANY_F ANY_F ANY_F -> pushMode(Spec);

mode Spec;

A_SPEC      : 'A' -> mode(Def);
COMMENT     : ANY_F '*' ANY_F* EOL_F -> popMode;

mode Def;

D_SPACE     : ' '+ -> type(SPACE);
RECORD      : 'R' ;
KEY         : 'K' ;
IDENTIFIER  : [A-Z0-9]+ -> mode(Type);

mode Type;

TY_SPACE    : ' '+ -> type(SPACE);
SIZE        : [0-9]+;
TYPE        : [ALPSTZ];
TEXT        : 'TEXT' -> mode(Text);
TY_EOL      : EOL_F -> type(EOL), popMode;

mode Text;

LPAR        : '(';
RPAR        : ')';
QUOTE       : '\'';
DESCRIPTION : ~[\r\n']+;
TE_EOL      : EOL_F -> type(EOL), popMode;

// Common fragments

fragment ANY_F      : ~[\r\n] ;
fragment EOL_F      : '\r'? '\n' ;
