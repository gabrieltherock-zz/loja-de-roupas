package boundary;

import control.RoupaControl;
import dao.exceptions.RoupaException;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.entity.Compra;
import model.entity.Endereco;
import model.entity.Roupa;
import model.entity.Usuario;
import model.enums.Sexo;
import model.enums.Tamanho;
import model.enums.Tecido;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CadastroProdutoBoundary implements PaneStrategy, ProdutorComando{

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelTamanho = new Label("Tamanho");
    private Label labelTecido = new Label("Tecido");
    private Label labelSexo = new Label("Sexo");
    private Label labelQuantidade = new Label("Qtd");
    private Label labelMarca = new Label("Marca");
    private Label labelModelo = new Label("Modelo");
    private Label labelCor = new Label("Cor");
    private Label labelDescricao = new Label("Decricao");
    private Label labelValor = new Label("Valor");

    private ComboBox<String> comboBoxTamanho = new ComboBox<>();
    private ComboBox<String> comboBoxTecido = new ComboBox<>();
    private ComboBox<String> comboBoxSexo = new ComboBox<>();
    private TextField textFieldQuantidade = new TextField();
    private TextField textFieldMarca = new TextField();
    private TextField textFieldModelo = new TextField();
    private TextField textFieldCor = new TextField();
    private TextField textFieldDescricao = new TextField();
    private TextField textFieldValor = new TextField();

    private Button buttonCadastrarProduto = new Button("Cadastrar");

    private RoupaControl roupaControl = new RoupaControl();

    public void vincularCampos() {
        StringConverter<? extends Number> numeroConverter = new IntegerStringConverter();
        StringConverter<? extends Number> numeroConverter1 = new DoubleStringConverter();
        Bindings.bindBidirectional(comboBoxTamanho.valueProperty(), roupaControl.getTamanhoProperty());
        Bindings.bindBidirectional(comboBoxTecido.valueProperty(), roupaControl.getTecidoProperty());
        Bindings.bindBidirectional(comboBoxSexo.valueProperty(), roupaControl.getSexoProperty());
        Bindings.bindBidirectional(	textFieldQuantidade.textProperty(),
                roupaControl.getQuantidadeProperty(),
                (StringConverter<Number>) numeroConverter);
        Bindings.bindBidirectional(textFieldMarca.textProperty(), roupaControl.getMarcaProperty());
        Bindings.bindBidirectional(textFieldModelo.textProperty(), roupaControl.getModeloProperty());
        Bindings.bindBidirectional(textFieldCor.textProperty(), roupaControl.getCorProperty());
        Bindings.bindBidirectional(textFieldDescricao.textProperty(), roupaControl.getDescricaoProperty());
        Bindings.bindBidirectional(	textFieldValor.textProperty(),
                roupaControl.getValorProperty(),
                (StringConverter<Number>) numeroConverter1);
    }

    public CadastroProdutoBoundary() {
        vincularCampos();

        pane.getChildren().addAll(labelTamanho, labelTecido, labelSexo, labelQuantidade, labelMarca, labelModelo,
                labelCor, labelDescricao, labelValor, comboBoxTamanho, comboBoxTecido, comboBoxSexo, textFieldQuantidade,
                textFieldMarca, textFieldModelo, textFieldCor, textFieldDescricao, textFieldValor, buttonCadastrarProduto
        );

        //1 Tecido
        labelTecido.relocate(16,55);
        comboBoxTecido.setMinSize(155, 27);
        comboBoxTecido.relocate(110,52);
        comboBoxTecido.getItems().addAll(Arrays.stream(Tecido.values())
                .map(Tecido::getNome)
                .collect(Collectors.toList()));

        //2 Tamanho
        labelTamanho.relocate(306,55);
        comboBoxTamanho.setMinSize(155, 27);
        comboBoxTamanho.relocate(359, 52);
        comboBoxTamanho.getItems().addAll(Arrays.stream(Tamanho.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        //3 Sexo
        labelSexo.relocate(16,109);
        comboBoxSexo.setMinSize(155, 27);
        comboBoxSexo.relocate(110,106);
        comboBoxSexo.getItems().addAll(Arrays.stream(Sexo.values())
                .map(Sexo::getNome)
                .collect(Collectors.toList()));

        //4 Quantidade
        labelQuantidade.relocate(306, 109);
        textFieldQuantidade.setMinSize(155, 27);
        textFieldQuantidade.relocate(359, 106);

        //5 Marca
        labelMarca.relocate(16,165);
        textFieldMarca.setMinSize(155, 27);
        textFieldMarca.relocate(110,159);

        //6 Modelo
        labelModelo.relocate(306,165);
        textFieldModelo.setMinSize(155, 27);
        textFieldModelo.relocate(359,159);

        //7 Cor
        labelCor.relocate(16,217);
        textFieldCor.setMinSize(155, 27);
        textFieldCor.relocate(110,214);

        //8 Descricao
        labelDescricao.relocate(307,217);
        textFieldDescricao.setMinSize(155, 27);
        textFieldDescricao.relocate(359,214);

        //9 Ponto de Valor
        labelValor.relocate(16,269);
        textFieldValor.setMinSize(155, 27);
        textFieldValor.relocate(110,266);

        buttonCadastrarProduto.setMinSize(121, 65);
        buttonCadastrarProduto.setStyle("-fx-font-size:30");
        buttonCadastrarProduto.relocate(348, 312);
        buttonCadastrarProduto.setOnAction(e -> acionarComando("acessar"));
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada, Endereco enderecoEntrega) {
        return pane;
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
        try {
            roupaControl.salvarRoupa();
            this.assinanteComando.executarComando(comando);
        } catch (RoupaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro ao cadastrar produto!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
