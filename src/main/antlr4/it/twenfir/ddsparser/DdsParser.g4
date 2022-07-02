parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( A_SPEC* ( UNIQUE | ref | altseq ) )*
        A_SPEC* RECORD ( record = IDENTIFIER )
        ( format
        | pfile
        | text
        )*
        ( field 
        | key
        | omit
        | select
        )*
        A_SPEC* 
        EOF
        ;

altseq : ALTSEQ LPAR IDENTIFIER RPAR ;

format : A_SPEC* FORMAT LPAR ( IDENTIFIER ) RPAR ;

ref : REF LPAR ( ( ref_lib = IDENTIFIER | CONSTANT ) SLASH )? ref_file = IDENTIFIER RPAR ;

field : A_SPEC* IDENTIFIER
        ( dataType | REFERENCE )
        ( A_SPEC* ALWNULL
        | alias
        | ccsid
        | dft
        | editCode
        | editWord
        | heading
        | pfile
        | refField
        | sst
        | text
        | values
        )*
        ;

dataType : ( NUMBER | NUMBER? TYPE ) NUMBER? ;

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

sst : A_SPEC* SST LPAR IDENTIFIER NUMBER NUMBER? RPAR ;

values : A_SPEC* VALUES LPAR ( QUOTE STRING QUOTE )+ RPAR ;

description : LPAR descriptionElement ( ( MINUS | PLUS )? A_SPEC* descriptionElement )* RPAR ;

descriptionElement : QUOTE ( STRING_START A_SPEC* )* STRING? QUOTE ;

key :   A_SPEC* KEY IDENTIFIER 
        ( A_SPEC* 
          ( DESCEND
          | NOALTSEQ
          )
        )* ;

omit : A_SPEC* OMIT IDENTIFIER comp? ;

select : A_SPEC* SELECT IDENTIFIER comp? ;

comp : COMP LPAR REL_OP ( QUOTE STRING QUOTE | NUMBER ) RPAR ;

