package model.enums;

public enum Sexo {
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino"),
    UNISSEX(3, "Unissex");

    private Integer id;
    private String nome;

    Sexo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static Sexo getById(Integer id) {
        for (Sexo s : values()) {
            if (s.id.equals(id))
                return s;
        }
        return null;
    }

    public static Sexo getByNome(String nome) {
        for (Sexo s : values()) {
            if (s.nome.equals(nome))
                return s;
        }
        return null;
    }
}
