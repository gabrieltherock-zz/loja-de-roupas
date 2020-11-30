package boundary;

import control.CompraControl;
import control.RoupaControl;
import dao.exceptions.CompraException;
import dao.exceptions.RoupaException;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.entity.Compra;
import model.entity.Endereco;
import model.entity.Roupa;
import model.entity.Usuario;
import model.enums.Pagamento;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CompraBoundary implements PaneStrategy, ProdutorComando{

    Compra compra = new Compra();

    private Roupa roupaSelecionada;

    private Usuario usuarioLogado = new Usuario();

    private Pagamento pagamento;

    private int quantidade = 1;

    private double total;

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelItem = new Label("Item");
	private Label labelQuantidade = new Label("Quantidade");
	private Label labelTotal = new Label("Total");
    private Label labelFormadePagamento = new Label("Forma de Pagamento");
    private Label labelModeloRoupa = new Label();
    private Label labelValor = new Label();
    private Label labelDesconto = new Label("10% de desconto!");
    
    private Spinner<Integer> spinnerQuantidade = new Spinner<>(1, Integer.MAX_VALUE, 1);
    private ComboBox<String> comboBoxFormadePagamento = new ComboBox<>();
    
    private Button buttonComprar = new Button("Comprar");
    private Button buttonVoltar = new Button("Voltar");

    private CompraControl compraControl = new CompraControl();

    private RoupaControl roupaControl = new RoupaControl();
    
    public CompraBoundary() {
        pane.getChildren().addAll(labelItem, labelQuantidade, labelTotal, labelFormadePagamento,
                labelModeloRoupa, labelValor, spinnerQuantidade, comboBoxFormadePagamento,
                buttonComprar, buttonVoltar, labelDesconto);

        //Item
        labelItem.relocate(33,85);
        labelItem.setStyle("-fx-font-size:20");
        labelModeloRoupa.setStyle("-fx-font-size:24");
        labelModeloRoupa.relocate(33, 109);
        labelModeloRoupa.setMaxSize(135, 32);

        //Quantidade
        labelQuantidade.relocate(215,111);
        labelQuantidade.setStyle("-fx-font-size:20");
        spinnerQuantidade.relocate(338, 113);
        spinnerQuantidade.setMinWidth(110);
        spinnerQuantidade.setMaxWidth(110);
        spinnerQuantidade.setEditable(true);
        spinnerQuantidade.setOnMouseClicked(e -> {
            quantidade = spinnerQuantidade.getValue();
            aplicaDesconto();
        });

        //Total
        labelTotal.relocate(33,172);
        labelTotal.setStyle("-fx-font-size:20");
        labelValor.setStyle("-fx-font-size:24");
        labelValor.relocate(33, 200);
        labelValor.setMaxSize(135, 32);

        //Pagamento
        labelFormadePagamento.relocate(215, 172);
        labelFormadePagamento.setStyle("-fx-font-size:20");
        comboBoxFormadePagamento.relocate(415, 175);
        comboBoxFormadePagamento.setMinWidth(110);
        comboBoxFormadePagamento.setMaxWidth(110);
        comboBoxFormadePagamento.getItems().addAll(Arrays.stream(Pagamento.values())
                .map(Pagamento::getNome)
                .collect(Collectors.toList()));
        labelDesconto.setVisible(false);
        labelDesconto.relocate(425, 200);

        buttonComprar.setOnAction(e -> {
            pagamento = Pagamento.getById(comboBoxFormadePagamento.getSelectionModel().getSelectedIndex() + 1);
            carregarRoupa();
            acionarComando("realizar compra");
        });

        buttonVoltar.setOnAction(e -> this.acionarComando("voltar para catalogo"));

        comboBoxFormadePagamento.setOnAction(e -> aplicaDesconto());

        buttonComprar.setMinSize(119, 32);
        buttonComprar.setStyle("-fx-font-size:24");
        buttonComprar.relocate(304, 289);

        buttonVoltar.setMinSize(119, 31);
        buttonVoltar.setStyle("-fx-font-size:24");
        buttonVoltar.relocate(76, 289);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada, Endereco enderecoEntrega) {
        this.usuarioLogado = usuarioLogado;
        this.roupaSelecionada = roupaSelecionada;
        aplicaDesconto();
        return pane;
    }

    private void aplicaDesconto() {
        if (comboBoxFormadePagamento.getSelectionModel().getSelectedItem() != null &&
                comboBoxFormadePagamento.getSelectionModel().getSelectedItem().equals("Boleto")) {
            labelDesconto.setVisible(true);
            total = calculaTotal(quantidade, true);
        } else {
            total = calculaTotal(quantidade, false);
            labelDesconto.setVisible(false);
        }
        carregarRoupa();
    }

    private void carregarRoupa() {
        labelModeloRoupa.setText(roupaSelecionada.getModelo());
        labelValor.setText(NumberFormat.getCurrencyInstance().format(total));
    }

    private double calculaTotal(int quantidade, boolean desconto) {
        double total = roupaSelecionada.getValor() * quantidade;
        return desconto ? total * 0.9 : total;
    }

    private void realizarCompra() {
        compra.setPagamento(pagamento);
        compra.setQuantidade(quantidade);
        compra.setRoupa(roupaSelecionada);
        compra.setUsuario(usuarioLogado);
        compra.setTotal(total);
        try {
            compra = compraControl.realizarCompra(compra);
            roupaSelecionada = roupaControl.descontaEstoque(roupaSelecionada);
            compra.setRoupa(roupaSelecionada);
            LoginBoundary.setCompraRealizada(compra);
            LoginBoundary.setRoupaSelecionada(roupaSelecionada);
        } catch (CompraException | RoupaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro ao realizar compra!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
        if (comando.equals("realizar compra"))
            realizarCompra();
        this.assinanteComando.executarComando(comando);
    }
}
