CREATE TABLE TB_USUARIO_CONTATOS (
     id_Usuario          RAW(16)             NOT NULL,
     nome                VARCHAR2(255 CHAR)  NOT NULL,
     email               VARCHAR2(255 CHAR)  NOT NULL   UNIQUE,
     senha               VARCHAR2(255 CHAR)  NOT NULL,
     role                VARCHAR2(255 CHAR)  DEFAULT 'user',
     PRIMARY KEY (id_usuario)
)