CREATE TABLE DESPESAS(
    ID BIGSERIAL,
    DESCRICAO VARCHAR(255),
    VALOR DECIMAL(10,2),
    DATA DATE,

    CONSTRAINT PK_DESPESA PRIMARY KEY (ID)
);