parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   UNIQUE?
        RECORD ( record = IDENTIFIER )
        ( FORMAT LPAR ( format = IDENTIFIER ) RPAR
        | field+ 
          key*
        ) 
        EOF ;

field : IDENTIFIER dataType ( text | colhdg | edtwrd )* ;

dataType : SIZE TYPE? SIZE? ;

text : TEXT description ;

colhdg : COLHDG description ;

edtwrd : EDTWRD description ;

description : LPAR descriptionElement ( PLUS? descriptionElement )* RPAR ;

descriptionElement : QUOTE DESC_START* DESCRIPTION QUOTE ;

key : KEY IDENTIFIER DESCEND?;
