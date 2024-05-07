CREATE TABLE TB_CONTATOS (
     id                  RAW(16)             NOT NULL,
     data_nascimento     DATE,
     email               VARCHAR2(255 CHAR),
     nome                VARCHAR2(255 CHAR),
     telefone            VARCHAR2(255 CHAR),
     PRIMARY KEY (id)
)