lexer grammar DdsLexer;

tokens { A_SPEC, CONSTANT, IDENTIFIER, LPAR, MINUS, NUMBER, PLUS, QUOTE, RPAR, SLASH, STRING, STRING_START }

PREFIX      : PREFIX_F -> channel(HIDDEN), pushMode(FormType);
PART_PREF   : ( ANY_F
              | ( ANY_F ANY_F )
              | ( ANY_F ANY_F ANY_F )
              | ( ANY_F ANY_F ANY_F ANY_F ) 
              ) 
              EOL_F -> channel(HIDDEN)
              ;
              
ST_EOL      : EOL_F+ -> channel(HIDDEN);

mode FormType;

SP_A_SPEC   : A_F -> type(A_SPEC), mode(Condition);
SP_SPACE    : ' ' -> channel(HIDDEN), mode(Condition);
COMMENT     : ANY_F? '*' ANY_F* -> channel(HIDDEN);
SP_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Condition;

CN_SPACE    : '          ' -> channel(HIDDEN), mode(NameType);
CN_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode NameType;

JOIN_DEF    : 'J' -> mode(Reserved);
KEY         : 'K' -> mode(Reserved);
OMIT        : 'O' -> mode(Reserved);
RECORD      : 'R' -> mode(Reserved);
SELECT      : 'S' -> mode(Reserved);
NT_SPACE    : ' ' -> channel(HIDDEN), mode(Reserved);

mode Reserved;

RS_SPACE    : ' ' -> channel(HIDDEN), mode(Name);

mode Name;

NAME1       : IDS_F -> type(IDENTIFIER), mode(Ns9);
NAME2       : IDS_F IDC_F -> type(IDENTIFIER), mode(Ns8);
NAME3       : IDS_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns7);
NAME4       : IDS_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns6);
NAME5       : IDS_F IDC_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns5);
NAME6       : IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns4);
NAME7       : IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns3);
NAME8       : IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns2);
NAME9       : IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Ns1);
NAME10      : IDS_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F IDC_F -> type(IDENTIFIER), mode(Reference);
NM_SPACE    : '          ' -> channel(HIDDEN), mode(Reference);
NM_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns9;

NS9_SPACE   : '         ' -> channel(HIDDEN), mode(Reference);
NS9_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns8;

NS8_SPACE   : '        ' -> channel(HIDDEN), mode(Reference);
NS8_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns7;

NS7_SPACE   : '       ' -> channel(HIDDEN), mode(Reference);
NS7_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns6;

NS6_SPACE   : '      ' -> channel(HIDDEN), mode(Reference);
NS6_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns5;

NS5_SPACE   : '     ' -> channel(HIDDEN), mode(Reference);
NS5_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns4;

NS4_SPACE   : '    ' -> channel(HIDDEN), mode(Reference);
NS4_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns3;

NS3_SPACE   : '   ' -> channel(HIDDEN), mode(Reference);
NS3_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns2;

NS2_SPACE   : '  ' -> channel(HIDDEN), mode(Reference);
NS2_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Ns1;

NS1_SPACE   : ' ' -> channel(HIDDEN), mode(Reference);
NS1_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Reference;

REFERENCE   : 'R' -> mode(Length);
RF_SPACE    : ' ' -> channel(HIDDEN), mode(Length); 
RF_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Length;

LEN5        : [0-9+-] [0-9] [0-9] [0-9] [0-9] -> type(NUMBER), mode(DataType);
LN1_SPACE   : ' ' -> channel(HIDDEN), mode(Len4);
LN2_SPACE   : '  ' -> channel(HIDDEN), mode(Len3);
LN3_SPACE   : '   ' -> channel(HIDDEN), mode(Len2);
LN4_SPACE   : '    ' -> channel(HIDDEN), mode(Len1);
LN5_SPACE   : '     ' -> channel(HIDDEN), mode(DataType);
LN_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Len4;

LEN4        : [0-9+-] [0-9] [0-9] [0-9] -> type(NUMBER), mode(DataType);
LN4_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Len3;

LEN3        : [0-9+-] [0-9] [0-9] -> type(NUMBER), mode(DataType);
LN3_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Len2;

LEN2        : [0-9+-] [0-9] -> type(NUMBER), mode(DataType);
LN2_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode Len1;

LEN1        : [0-9] -> type(NUMBER), mode(DataType);
LN1_EOL     : EOL_F+ -> channel(HIDDEN), popMode;

mode DataType;

TYPE        : [ABFHLPSTZ5] -> mode(Precision);
DT_SPACE    : ' ' -> channel(HIDDEN), mode(Precision);
DT_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Precision;

PREC2       : [0-9] [0-9] -> type(NUMBER), mode(Usage);
PREC1       : [0-9] -> type(NUMBER), mode(Usage);
PR_SPACE2   : '  ' -> channel(HIDDEN), mode(Usage);
PR_SPACE1   : ' ' -> channel(HIDDEN);
PR_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Usage;

USAGE       : [BIN] -> mode(Location);
US_SPACE    : ' ' -> channel(HIDDEN), mode(Location);
US_EOL      : EOL_F+ -> channel(HIDDEN), popMode;

mode Location;

LC_SPACE    : '      ' -> channel(HIDDEN), mode(Keyword);

mode Keyword;

ALIAS       : 'ALIAS';
ALL         : 'ALL';
ALTSEQ      : 'ALTSEQ';
ALWNULL     : 'ALWNULL';
COLHDG      : 'COLHDG';
COMP        : 'C' 'O'? 'MP';
CCSID       : 'CCSID';
DESCEND     : 'DESCEND';
DFT         : 'DFT';
EDTCDE      : 'EDTCDE' -> mode(Edtcde);
EDTWRD      : 'EDTWRD';
FIFO		: 'FIFO';
FORMAT      : 'FORMAT';
JDFTVAL     : 'JDFTVAL';
JFILE       : 'JFILE';
JFLD        : 'JFLD';
JOIN        : 'JOIN';
JREF        : 'JREF';
NOALTSEQ    : 'NOALTSEQ';
PFILE       : 'PFILE';
REF         : 'REF';
REFFLD      : 'REFFLD';
SST         : 'SST';
TEXT        : 'TEXT';
UNIQUE      : 'UNIQUE';
VALUES      : 'VALUES';
KW_LPAR     : '(' -> type(LPAR), mode(Expression);
KW_SPACE    : ' '+ -> channel(HIDDEN);
KW_EOL      : EOL_F+ -> channel(HIDDEN), popMode;
MINUS          : '-' -> mode(KeywordCont);
PLUS           : '+' -> mode(KeywordCont);

mode KeywordCont;

KC_EOL   : EOL_F+ -> channel(HIDDEN), popMode;

mode Expression;

EX_RPAR         : ')' -> type(RPAR), mode(Keyword);
REL_OP          : 'EQ' | 'NE' | 'LT' | 'NL' | 'GT' | 'NG' | 'LE' | 'GE' ;
EX_IDENTIFIER   : IDENTIFIER_F -> type(IDENTIFIER);
EX_CONSTANT     : CONSTANT_F -> type(CONSTANT);
EX_SPACE        : ' '+ -> channel(HIDDEN);
EX_SLASH        : '/' -> type(SLASH);
EX_NUMBER       : NUMBER_F -> type(NUMBER);
EX_QUOTE        : '\'' -> type(QUOTE), mode(String);
EX_PLUS			: '+' -> type(PLUS);
EX_MINUS		: '-' -> type(MINUS);
EX_EOL			: EOL_F+ -> channel(HIDDEN), mode(ExprPref);

mode ExprPref;

EP_PREFIX		: PREFIX_F -> channel(HIDDEN), mode(ExprForm);
EP_PART_PREF	: ( ANY_F
				  | ( ANY_F ANY_F )
                  | ( ANY_F ANY_F ANY_F )
                  | ( ANY_F ANY_F ANY_F ANY_F ) 
                  ) 
	              EOL_F -> channel(HIDDEN)
    	          ;
      
EP_EOL      : EOL_F+ -> channel(HIDDEN);

mode ExprForm;

EF_A_SPEC   : A_F -> type(A_SPEC), mode(ExprMiddle);
EF_SPACE    : ' ' -> channel(HIDDEN), mode(ExprMiddle);
EF_COMMENT	: ANY_F? '*' ANY_F* -> channel(HIDDEN);
EF_EOL      : EOL_F+ -> channel(HIDDEN), mode(ExprPref);

mode ExprMiddle;

EM_SPACE	: '                                      ' -> channel(HIDDEN), mode(Expression);

mode Edtcde;

EC_LPAR     : '(' -> type(LPAR);
EC_RPAR     : ')' -> type(RPAR), mode(Keyword);
EDITCODE    : [KNZ3];

mode String;

STRING_START_MINUS : STRING_START_F '-' EOL_F -> type(STRING_START), mode(StringPrfMinus);
STRING_START_PLUS  : STRING_START_F '+' EOL_F -> type(STRING_START), mode(StringPrfPlus);
STRING_START_EMPTY : STRING_START_F EOL_F -> type(STRING_START), mode(StringPrfPlus);
STRING             : STRING_START_F [+-]? -> type(STRING);
ST_QUOTE           : '\'' -> type(QUOTE), mode(Expression);

mode StringPrfMinus;

SPM_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(StringSpecMinus);

mode StringSpecMinus;

SSM_A_SPEC  : A_F -> type(A_SPEC);
SSM_SPACE   : '                                      ' -> channel(HIDDEN), mode(String);

mode StringPrfPlus;

SPP_PREFIX  : PREFIX_F -> channel(HIDDEN), mode(StringSpecPlus);

mode StringSpecPlus;

SSP_A_SPEC  : A_F -> type(A_SPEC);
SSP_SPACE   : ' '+ -> channel(HIDDEN), mode(String);

// Common fragments

fragment ANY_F              : ~[\r\n] ;
fragment EOL_F              : '\r'? '\n' ;
fragment PREFIX_F           : ANY_F ANY_F ANY_F ANY_F ANY_F ;
fragment A_F                : [Aa] ;
fragment IDS_F              : [A-Z$\u00a3\u00a7] ;
fragment IDC_F              : [A-Z0-9$_\u00a3\u00a7] ;
fragment IDENTIFIER_F       : IDS_F IDC_F* ;
fragment CONSTANT_F         : '*'[A-Z][A-Z0-9_]* ;
fragment NUMBER_F           : [+-]? [0-9]+;
fragment STRING_START_F       : ((~[\r\n'])|('\'\''))+ ;
