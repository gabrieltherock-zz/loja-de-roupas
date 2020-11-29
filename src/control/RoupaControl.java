package control;

import boundary.view.RoupasView;
import dao.RoupaDAO;
import dao.exceptions.RoupaException;
import dao.impl.RoupaDAOImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entity.Roupa;
import model.enums.Sexo;
import model.enums.Tamanho;
import model.enums.Tecido;

import java.util.List;

public class RoupaControl {

    private StringProperty tamanhoProperty = new SimpleStringProperty("M");
    private StringProperty tecidoProperty = new SimpleStringProperty("Fibra de soja");
    private StringProperty sexoProperty = new SimpleStringProperty("Feminino");
    private IntegerProperty quantidadeProperty = new SimpleIntegerProperty(100);
    private StringProperty marcaProperty = new SimpleStringProperty("Lacoste");
    private StringProperty modeloProperty = new SimpleStringProperty("Camisa sustentavel");
    private StringProperty corProperty = new SimpleStringProperty("Vermelho");
    private StringProperty descricaoProperty = new SimpleStringProperty("Uma bela camisa, bastante confortavel");
    private DoubleProperty valorProperty = new SimpleDoubleProperty(100.00);

    private RoupaDAO roupaDAO = new RoupaDAOImpl();

    public Roupa getRoupa() {
        Roupa roupa = new Roupa();
        roupa.setTamanho(Tamanho.valueOf(tamanhoProperty.get()));
        roupa.setTecido(Tecido.getByNome(tecidoProperty.get()));
        roupa.setSexo(Sexo.getByNome((sexoProperty.get())));
        roupa.setQuantidade(quantidadeProperty.get());
        roupa.setMarca(marcaProperty.get());
        roupa.setModelo(modeloProperty.get());
        roupa.setCor(corProperty.get());
        roupa.setDescricao(descricaoProperty.get());
        roupa.setValor(valorProperty.get());
        return roupa;
    }

    public void setUsuario(Roupa roupa) {
        if (roupa != null) {
            tamanhoProperty.set(roupa.getTamanho().name());
            tecidoProperty.set(roupa.getTecido().name());
            sexoProperty.set(roupa.getSexo().name());
            quantidadeProperty.set(roupa.getQuantidade());
            marcaProperty.set(roupa.getMarca());
            modeloProperty.set(roupa.getModelo());
            corProperty.set(roupa.getCor());
            descricaoProperty.set(roupa.getDescricao());
            valorProperty.set(roupa.getValor());
        }
    }

    public List<RoupasView> carregarRoupasView() throws RoupaException {
        return roupaDAO.carregarRoupasView();
    }

    public Roupa carregarRoupa(Roupa roupa) throws RoupaException {
        return roupaDAO.carregarRoupa(roupa);
    }

    public Roupa salvarRoupa() throws RoupaException {
        return roupaDAO.salvarRoupa(getRoupa());
    }

    public Roupa descontaEstoque(Roupa roupa) throws RoupaException {
        return roupaDAO.atualizarRoupa(roupa);
    }

    public StringProperty getTamanhoProperty() {
        return tamanhoProperty;
    }

    public StringProperty getTecidoProperty() {
        return tecidoProperty;
    }

    public StringProperty getSexoProperty() {
        return sexoProperty;
    }

    public IntegerProperty getQuantidadeProperty() {
        return quantidadeProperty;
    }

    public StringProperty getMarcaProperty() {
        return marcaProperty;
    }

    public StringProperty getModeloProperty() {
        return modeloProperty;
    }

    public StringProperty getCorProperty() {
        return corProperty;
    }

    public StringProperty getDescricaoProperty() {
        return descricaoProperty;
    }

    public DoubleProperty getValorProperty() {
        return valorProperty;
    }
}
