package model.entity;

import model.enums.Sexo;
import model.enums.Tamanho;
import model.enums.Tecido;

public class Roupa {

    private long id;
    private Tamanho tamanho;
    private Tecido tecido;
    private Sexo sexo;
    private int quantidade;
    private String marca;
    private String modelo;
    private String cor;
    private String descricao;
    private double valor;

    public Roupa() {
    }

    public Roupa(long id, Tamanho tamanho, Tecido tecido, Sexo sexo, int quantidade, String marca,
                 String modelo, String cor, String descricao, double valor) {
        this.id = id;
        this.tamanho = tamanho;
        this.tecido = tecido;
        this.sexo = sexo;
        this.quantidade = quantidade;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.descricao = descricao;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Tecido getTecido() {
        return tecido;
    }

    public void setTecido(Tecido tecido) {
        this.tecido = tecido;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
