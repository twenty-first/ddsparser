parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( A_SPEC? ( UNIQUE | REF LPAR ref = IDENTIFIER RPAR ) )?
        A_SPEC? R_SPEC ( record = IDENTIFIER ) text?
        ( A_SPEC? FORMAT LPAR ( format = IDENTIFIER ) RPAR
        | field+ 
          key*
        ) 
        EOF ;

field : A_SPEC? IDENTIFIER
        ( dataType | R_SPEC )
        ( A_SPEC? ALWNULL
        | colhdg
        | A_SPEC? CCSID LPAR NUMBER RPAR
        | edtwrd
        | reffld
        | text
        | values
        )* ;

dataType : SIZE TYPE? SIZE? ;

text : A_SPEC? TEXT description ;

colhdg : A_SPEC? COLHDG description ;

edtwrd : A_SPEC? EDTWRD description ;

reffld : A_SPEC? REFFLD LPAR IDENTIFIER RPAR ;

values : A_SPEC? VALUES LPAR ( QUOTE VALUE QUOTE )+ RPAR ;

description : LPAR descriptionElement ( PLUS? A_SPEC? descriptionElement )* RPAR ;

descriptionElement : QUOTE ( DESC_START A_SPEC? )* DESCRIPTION QUOTE ;

key : A_SPEC? KEY IDENTIFIER DESCEND?;
