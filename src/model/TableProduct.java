package model;

import javafx.beans.property.SimpleStringProperty;

public class TableProduct {

    private final SimpleStringProperty  modelo;

    private final SimpleStringProperty  marca;

    private final SimpleStringProperty  preco;

    public TableProduct(String modelo, String marca, String preco) {
        this.modelo = new SimpleStringProperty(modelo);
        this.marca = new SimpleStringProperty(marca);
        this.preco = new SimpleStringProperty(preco);
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getPreco() {
        return preco.get();
    }

    public void setPreco(String preco) {
        this.preco.set(preco);
    }
}
