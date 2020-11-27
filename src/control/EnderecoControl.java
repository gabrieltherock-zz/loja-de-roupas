package control;

import dao.EnderecoDAO;
import dao.EnderecoDAOImpl;
import dao.exceptions.EnderecoException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entity.Endereco;

public class EnderecoControl {

    private StringProperty cepProperty = new SimpleStringProperty("58693874");
    private StringProperty ruaProperty = new SimpleStringProperty("Rua dos Javeiros");
    private IntegerProperty numeroProperty = new SimpleIntegerProperty(123);
    private StringProperty complementoProperty = new SimpleStringProperty("Portao em formato de xicara");
    private StringProperty referenciaProperty = new SimpleStringProperty("Perto do Kotlin");

    EnderecoDAO enderecoDAO = new EnderecoDAOImpl();

    public Endereco getEndereco() {
        Endereco e = new Endereco();
        e.setCep(cepProperty.get());
        e.setRua(ruaProperty.get());
        e.setNumero(numeroProperty.get());
        e.setComplemento(complementoProperty.get());
        e.setReferencia(referenciaProperty.get());
        return e;
    }

    public void setEndereco(Endereco e) {
        cepProperty.set(e.getCep());
        ruaProperty.set(e.getRua());
        numeroProperty.set(e.getNumero());
        complementoProperty.set(e.getComplemento());
        referenciaProperty.set(e.getReferencia());
    }

    public Endereco adicionar() throws EnderecoException {
        return enderecoDAO.adicionar(getEndereco());
    }

    public StringProperty getCepProperty() {
        return cepProperty;
    }

    public StringProperty getRuaProperty() {
        return ruaProperty;
    }

    public IntegerProperty getNumeroProperty() {
        return numeroProperty;
    }

    public StringProperty getComplementoProperty() {
        return complementoProperty;
    }

    public StringProperty getReferenciaProperty() {
        return referenciaProperty;
    }
}
