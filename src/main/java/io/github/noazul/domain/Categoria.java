package io.github.noazul.domain;

public enum Categoria {

    ALIMENTACAO ("Alimentação"),
    SAUDE ("Saúde"),
    MORADIA ("Moradia"),
    TRANSPORTE ("Transporte"),
    EDUCACAO ("Educação"),
    LAZER ("Lazer"),
    IMPREVISTOS ("Imprevistos"),
    OUTRAS ("Outras");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
