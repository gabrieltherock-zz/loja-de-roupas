package model.enums;

public enum Pagamento {
    BOLETO(1),
    CREDITO(2),
    DEBITO(3);

    private Integer id;

    Pagamento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
