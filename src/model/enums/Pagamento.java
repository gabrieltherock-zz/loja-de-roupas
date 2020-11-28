package model.enums;

public enum Pagamento {
    BOLETO(1, "Boleto"),
    CREDITO(2, "Crédito"),
    DEBITO(3, "Débito");

    private Integer id;
    private String nome;

    Pagamento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public static Pagamento getById(Integer id) {
        for (Pagamento p : values()) {
            if (p.id.equals(id))
                return p;
        }
        return null;
    }
}
