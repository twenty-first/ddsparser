parser grammar DdsParser;

options
{
	tokenVocab = DdsLexer ;
}

dds :   ( A_SPEC* ( FIFO | JDFTVAL | UNIQUE | ref | altseq ) )*
        A_SPEC* RECORD ( record = IDENTIFIER )
        ( format
        | jfile
        | physicalFile
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

format : A_SPEC* FORMAT LPAR ( fileName ) RPAR ;

jfile : A_SPEC* JFILE LPAR IDENTIFIER+ RPAR ;

join : A_SPEC* JOIN_DEF JOIN LPAR IDENTIFIER IDENTIFIER RPAR jfld* ;

jfld: A_SPEC* JFLD LPAR IDENTIFIER IDENTIFIER RPAR ;

physicalFile : A_SPEC* PFILE LPAR fileName+ RPAR ;

ref : REF LPAR ( ( ref_lib = IDENTIFIER | CONSTANT ) SLASH )? ref_file = IDENTIFIER RPAR ;

text : A_SPEC* TEXT description ;

field : A_SPEC* IDENTIFIER
        REFERENCE?
        PLUS?
        dataType?
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
        | physicalFile
        | refField
        | sst
        | text
        | values
        )*
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

values : A_SPEC* VALUES LPAR value+ RPAR ;

value : QUOTE STRING QUOTE | NUMBER ;

description : LPAR descriptionElement ( ( MINUS | PLUS )? A_SPEC* descriptionElement )* RPAR ;

descriptionElement : QUOTE ( STRING_START A_SPEC* )* STRING? QUOTE ;

key :   A_SPEC* KEY IDENTIFIER 
        ( A_SPEC* 
          ( DESCEND
          | NOALTSEQ
          )
        )* ;

omit : A_SPEC* OMIT ( IDENTIFIER ( comp | values )? | ALL );

select : A_SPEC* SELECT ( IDENTIFIER ( comp | values )? | ALL );

comp : COMP LPAR REL_OP ( QUOTE STRING QUOTE | NUMBER ) RPAR ;

fileName : ( lib = IDENTIFIER SLASH )? name = IDENTIFIER ;
