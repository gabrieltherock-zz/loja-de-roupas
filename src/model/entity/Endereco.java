package model.entity;

public class Endereco {

    private long id;
    private Usuario usuario;
    private String cep;
    private String rua;
    private int numero;
    private String complemento;
    private String referencia;

    public Endereco(int id, Usuario usuario, String cep, String rua, int numero, String complemento, String referencia) {
        this.id = id;
        this.usuario = usuario;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
    }

    public void mostrarEndereco() {
        System.out.println("\nENDERECO_ID: " + id +
                "\nUSUARIO_ID: " + usuario.getId());
    }
    public Endereco() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
