CREATE TABLE USUARIOS(
     ID BIGSERIAL,
     NOME VARCHAR(255) NOT NULL,
     EMAIL VARCHAR(255) NOT NULL UNIQUE,
     SENHA VARCHAR(255) NOT NULL,

     CONSTRAINT PK_USUARIOS PRIMARY KEY (ID)
);

INSERT INTO USUARIOS (NOME, EMAIL, SENHA) VALUES ('KAIQUE DE MIRANDA', 'KBDEMIRANDA@HOTMAIL.COM', '$2a$10$OSKDZhGdVI/PnfilIsVaEONBXYpUhEun1k5sQiHqs7RUVJQpmCjPq');