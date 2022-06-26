parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( A_SPEC* ( UNIQUE | ref | altseq ) )?
        A_SPEC* R_SPEC ( record = IDENTIFIER )
        ( text
        | pfile
        )*
        ( A_SPEC* FORMAT LPAR ( format = IDENTIFIER ) RPAR
        | field* 
          key*
        )
        A_SPEC* 
        EOF
        ;

altseq : ALTSEQ LPAR IDENTIFIER RPAR ;

ref : REF LPAR ( ( ref_lib = IDENTIFIER | CONSTANT ) SLASH )? ref_file = IDENTIFIER RPAR ;

field : A_SPEC* IDENTIFIER
        ( dataType | R_SPEC )
        ( A_SPEC* ALWNULL
        | alias
        | ccsid
        | dft
        | editCode
        | editWord
        | heading
        | pfile
        | refField
        | text
        | values
        )*
        ;

dataType : ( SIZE | SIZE? TYPE ) SIZE? ;

text : A_SPEC* TEXT description ;

alias : A_SPEC* ALIAS LPAR IDENTIFIER RPAR ;

ccsid : A_SPEC* CCSID LPAR NUMBER RPAR ;

heading : A_SPEC* COLHDG description ;

dft : A_SPEC* DFT description ;

editCode : A_SPEC* EDTCDE LPAR EDITCODE RPAR ;

editWord : A_SPEC* EDTWRD description ;

pfile : A_SPEC* PFILE LPAR IDENTIFIER RPAR ;

refField : A_SPEC* REFFLD LPAR 
        ref_field = IDENTIFIER 
        ( ( ( ref_lib = IDENTIFIER | con_lib = CONSTANT ) SLASH )?
          ( ref_file = IDENTIFIER | con_file = CONSTANT )
        )?
        RPAR
        ;

values : A_SPEC* VALUES LPAR ( QUOTE VALUE QUOTE )+ RPAR ;

description : LPAR descriptionElement ( ( MINUS | PLUS )? A_SPEC* descriptionElement )* RPAR ;

descriptionElement : QUOTE ( DESC_START A_SPEC* )* DESCRIPTION? QUOTE ;

key : A_SPEC* KEY IDENTIFIER DESCEND?;
