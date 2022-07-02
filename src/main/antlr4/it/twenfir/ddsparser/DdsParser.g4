parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( A_SPEC* ( JDFTVAL | UNIQUE | ref | altseq ) )*
        A_SPEC* RECORD ( record = IDENTIFIER )
        ( format
        | jfile
        | pfile
        | text
        )*
        join?
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

jfile : A_SPEC* JFILE LPAR IDENTIFIER+ RPAR ;

join : A_SPEC* JOIN_DEF JOIN LPAR IDENTIFIER IDENTIFIER RPAR jfld* ;

jfld: A_SPEC* JFLD LPAR IDENTIFIER IDENTIFIER RPAR ;

pfile : A_SPEC* PFILE LPAR IDENTIFIER RPAR ;

ref : REF LPAR ( ( ref_lib = IDENTIFIER | CONSTANT ) SLASH )? ref_file = IDENTIFIER RPAR ;

text : A_SPEC* TEXT description ;

field : A_SPEC* IDENTIFIER
        ( ( dataType | REFERENCE )?
          USAGE?
          ( A_SPEC* ALWNULL
          | alias
          | ccsid
          | comp
          | dft
          | editCode
          | editWord
          | heading
          | jref
          | pfile
          | refField
          | sst
          | text
          | values
          )*
        )?
        ;

alias : A_SPEC* ALIAS LPAR IDENTIFIER RPAR ;

ccsid : A_SPEC* CCSID LPAR NUMBER RPAR ;

dft : A_SPEC* DFT description ;

dataType : ( NUMBER | NUMBER? TYPE ) NUMBER? ;

editCode : A_SPEC* EDTCDE LPAR EDITCODE RPAR ;

editWord : A_SPEC* EDTWRD description ;

heading : A_SPEC* COLHDG description ;

jref: A_SPEC* JREF LPAR NUMBER RPAR ;

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

omit : A_SPEC* OMIT ( IDENTIFIER comp? | ALL );

select : A_SPEC* SELECT ( IDENTIFIER comp? | ALL );

comp : COMP LPAR REL_OP ( QUOTE STRING QUOTE | NUMBER ) RPAR ;

