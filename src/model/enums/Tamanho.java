package model.enums;

public enum Tamanho {
    PP(1),
    P(2),
    M(3),
    G(4),
    GG(5);

    private Integer id;

    Tamanho(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
