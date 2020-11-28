package model.entity;

import model.enums.Pagamento;

public class Compra {

    private long id;
    private Usuario usuario;
    private Roupa roupa;
    private Pagamento pagamento;
    private int quantidade;
    private double total;

    public Compra(long id, Usuario usuario, Roupa roupa, Pagamento pagamento, int quantidade, double total) {
        this.id = id;
        this.usuario = usuario;
        this.roupa = roupa;
        this.pagamento = pagamento;
        this.quantidade = quantidade;
        this.total = total;
    }

    public Compra() {
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

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
