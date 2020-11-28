package boundary;

import control.CompraControl;
import control.RoupaControl;
import dao.exceptions.CompraException;
import dao.exceptions.RoupaException;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import model.entity.Compra;
import model.entity.Roupa;
import model.entity.Usuario;
import model.enums.Pagamento;

import java.text.DecimalFormat;
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

    private CompraControl compraControl = new CompraControl();

    private RoupaControl roupaControl = new RoupaControl();
    
    public CompraBoundary() {
        pane.getChildren().addAll(labelItem, labelQuantidade, labelTotal, labelFormadePagamento,
                labelModeloRoupa, labelValor, spinnerQuantidade, comboBoxFormadePagamento, buttonComprar, labelDesconto);

        //Item
        labelItem.relocate(33,85);
        labelItem.setStyle("-fx-font-size:20");
        labelModeloRoupa.setStyle("-fx-font-size:24");
        labelModeloRoupa.relocate(33, 109);

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

        comboBoxFormadePagamento.setOnAction(e -> aplicaDesconto());

        buttonComprar.setMinSize(119, 32);
        buttonComprar.setStyle("-fx-font-size:24");
        buttonComprar.relocate(209, 283);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada) {
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
            carregarRoupa();
        } else {
            total = calculaTotal(quantidade, false);
            carregarRoupa();
            labelDesconto.setVisible(false);
        }
    }

    private void carregarRoupa() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        labelModeloRoupa.setText(roupaSelecionada.getModelo());
        labelValor.setText("R$ " + df.format(total));
    }

    private double calculaTotal(int quantidade, boolean desconto) {
        double total = roupaSelecionada.getValor() * quantidade;
        return desconto ? total * 0.9 : total;
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
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
            e.printStackTrace();
        }
        this.assinanteComando.executarComando(comando);
    }
}
