package br.com.casadocodigo.loja.domain;

public enum TipoPreco {

    EBOOK("EBOOK"),
    IMPRESSO("IMPRESSO"),
    COMBO("COMBO");

    private final String name;

    TipoPreco(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
