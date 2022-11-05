ddsparser
=========
A parser for IBM's Data Description Specifications
--------------------------------------------------
![build](https://github.com/twenty-first/ddsparser/actions/workflows/build.yml/badge.svg)

`ddsparser` is a parser for DDS files, which are used on IBM i systems to specify record layouts and file formats. In particular, `ddspaser` recognizes DDS files used to describe the physical and logical files used for Record Level Access. RLA is a data management mechanism which predates relational databases. Physical files are somewhat analogous to database tables, while logical files are closer to materialized views. A possible use for `ddsparser` would be to collect the information necessary to recreate the data model of an RPG or COBOL application in a DBMS.

`ddsparser` is written in Java with the help of the [ANTLR parser generator](https://www.antlr.org/) and is available from the Maven Central repository.
