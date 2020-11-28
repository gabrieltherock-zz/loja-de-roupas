package model.enums;

public enum Pagamento {
    BOLETO("Boleto"),
    CREDITO("Crédito"),
    DEBITO("Débito");

    private String name;

    Pagamento(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
