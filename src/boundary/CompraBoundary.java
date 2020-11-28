package boundary;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import model.entity.Roupa;
import model.entity.Usuario;
import model.enums.Pagamento;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CompraBoundary implements PaneStrategy, ProdutorComando{

    private Roupa roupaSelecionada;

    private Usuario usuarioLogado = new Usuario();

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelItem = new Label("Item");
	private Label labelQuantidade = new Label("Quantidade");
	private Label labelTotal = new Label("Total");
    private Label labelFormadePagamento = new Label("Forma de Pagamento");
    private Label labelModeloRoupa = new Label("Camisa Polo");
    private Label labelValor = new Label("R$ 59,90");
    private Label labelDesconto = new Label("10% de desconto!");
    
    private Spinner<Integer> spinnerQuantidade = new Spinner<>(1, Integer.MAX_VALUE, 1);
    private ComboBox<String> comboBoxFormadePagamento = new ComboBox<>();
    
    private Button buttonComprar = new Button("Comprar");
    
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
                .map(Pagamento::getName)
                .collect(Collectors.toList()));
        labelDesconto.setVisible(false);
        labelDesconto.relocate(425, 200);

        buttonComprar.setOnAction(e -> {
            System.out.println(roupaSelecionada.getId());
            System.out.println(comboBoxFormadePagamento.getSelectionModel().getSelectedIndex() + 1);
            System.out.println(spinnerQuantidade.getValue());
        });

        comboBoxFormadePagamento.setOnAction(e -> {
            if (comboBoxFormadePagamento.getSelectionModel().getSelectedItem().equals("Boleto")){
                labelDesconto.setVisible(true);
            } else {
                labelDesconto.setVisible(false);
            }
        });

        buttonComprar.setMinSize(119, 32);
        buttonComprar.setStyle("-fx-font-size:24");
        buttonComprar.relocate(209, 283);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada) {
        this.usuarioLogado = usuarioLogado;
        this.roupaSelecionada = roupaSelecionada;
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
