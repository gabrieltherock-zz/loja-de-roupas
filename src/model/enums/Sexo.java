package model.enums;

public enum Sexo {
    MASCULINO(1),
    FEMININO(2),
    UNISSEX(3);

    private Integer id;

    Sexo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
