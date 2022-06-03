parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( UNIQUE | REF LPAR ref = IDENTIFIER RPAR )?
        R_SPEC ( record = IDENTIFIER )
        ( FORMAT LPAR ( format = IDENTIFIER ) RPAR
        | field+ 
          key*
        ) 
        EOF ;

field : IDENTIFIER ( dataType | R_SPEC ) ( text | colhdg | edtwrd | reffld | values )* ;

dataType : SIZE TYPE? SIZE? ;

text : TEXT description ;

colhdg : COLHDG description ;

edtwrd : EDTWRD description ;

reffld : REFFLD LPAR IDENTIFIER RPAR ;

values : VALUES LPAR ( QUOTE VALUE QUOTE )+ RPAR ;

description : LPAR descriptionElement ( PLUS? descriptionElement )* RPAR ;

descriptionElement : QUOTE DESC_START* DESCRIPTION QUOTE ;

key : KEY IDENTIFIER DESCEND?;
