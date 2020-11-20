package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompraBoundary implements PaneStrategy, ProdutorComando{

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelItem = new Label("Item");
	private Label labelQuantidade = new Label("Quantidade");
	private Label labelTotal = new Label("Total");
    private Label labelFormadePagamento = new Label("Forma de Pagamento");
    private Label labelModeloRoupa = new Label("Camisa Polo");
    private Label labelValor = new Label("R$ 59,90");
    
    private Spinner<String> spinnerQuantidade = new Spinner<>(1, Integer.MAX_VALUE, 1);
    private ComboBox<String> comboBoxFormadePagamento = new ComboBox<>();
    
    private Button buttonComprar = new Button("Comprar");
    
    public CompraBoundary() {
        pane.getChildren().addAll(labelItem, labelQuantidade, labelTotal, labelFormadePagamento,
                labelModeloRoupa, labelValor, spinnerQuantidade, comboBoxFormadePagamento, buttonComprar);

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
        comboBoxFormadePagamento.getItems().addAll("Boleto", "Débito", "Crédito");

        buttonComprar.setMinSize(119, 32);
        buttonComprar.setStyle("-fx-font-size:24");
        buttonComprar.relocate(209, 283);
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
        this.assinanteComando.executarComando(comando);
    }
}
