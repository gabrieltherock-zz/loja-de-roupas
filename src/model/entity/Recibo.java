package model.entity;

import java.util.Date;

public class Recibo {

    private long id;
    private Compra compra;
    private Date data;

    public Recibo(long id, Compra compra, Date data) {
        this.id = id;
        this.compra = compra;
        this.data = data;
    }

    public Recibo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
