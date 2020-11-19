package model.enums;

public enum Tecido {
    ALGODAO_ORGANICO(1, "Algodão orgânico"),
    CANHAMO(2, "Cânhamo"),
    FIBRA_DE_BANANEIRA(3, "Fibra de bananeira"),
    FIBRA_DE_LARANJA(4, "Fibra de laranja"),
    FIBRA_DE_SOJA(5, "Fibra de soja"),
    LENPUR(6, "Lenpur"),
    LINHO(7, "Linho"),
    LIOCEL(8, "Liocel"),
    MODAL(9, "Modal"),
    PINATEX(10, "Piñatex"),
    POLIAMIDA_BIODEGRADAVEL(11, "Poliamida biodegradavel"),
    QMILK(12, "Qmilk");

    private Integer id;

    private String nome;

    Tecido(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
