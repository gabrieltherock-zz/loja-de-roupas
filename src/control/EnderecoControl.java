package control;

import dao.EnderecoDAO;
import dao.impl.EnderecoDAOImpl;
import dao.exceptions.EnderecoException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entity.Endereco;
import model.entity.Usuario;

public class EnderecoControl {

    private StringProperty cepProperty = new SimpleStringProperty("58693874");
    private StringProperty ruaProperty = new SimpleStringProperty("Rua dos Javeiros");
    private IntegerProperty numeroProperty = new SimpleIntegerProperty(123);
    private StringProperty complementoProperty = new SimpleStringProperty("Portao em formato de xicara");
    private StringProperty referenciaProperty = new SimpleStringProperty("Perto do Kotlin");

    EnderecoDAO enderecoDAO = new EnderecoDAOImpl();

    public Endereco getEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep(cepProperty.get());
        endereco.setRua(ruaProperty.get());
        endereco.setNumero(numeroProperty.get());
        endereco.setComplemento(complementoProperty.get());
        endereco.setReferencia(referenciaProperty.get());
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        cepProperty.set(endereco.getCep());
        ruaProperty.set(endereco.getRua());
        numeroProperty.set(endereco.getNumero());
        complementoProperty.set(endereco.getComplemento());
        referenciaProperty.set(endereco.getReferencia());
    }

    public Endereco adicionar() throws EnderecoException {
        return enderecoDAO.adicionar(getEndereco());
    }

    public Endereco encontrarEndereco(Usuario usuario) throws EnderecoException {
        return enderecoDAO.encontrarEndereco(usuario);
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
