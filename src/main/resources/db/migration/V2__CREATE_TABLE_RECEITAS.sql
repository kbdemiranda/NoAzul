CREATE TABLE RECEITAS(
    ID BIGSERIAL,
    DESCRICAO VARCHAR(255),
    VALOR DECIMAL(10,2),
    DATA DATE,

    CONSTRAINT PK_RECEITAS PRIMARY KEY (ID)
);