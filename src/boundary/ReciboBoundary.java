package boundary;

import control.ReciboControl;
import dao.exceptions.ReciboException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.entity.Compra;
import model.entity.Endereco;
import model.entity.Recibo;
import model.entity.Roupa;
import model.entity.Usuario;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class ReciboBoundary implements PaneStrategy, ProdutorComando {

    private Recibo reciboGerado = new Recibo();

    private Endereco enderecoEntrega = new Endereco();

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Button buttonVoltar = new Button("Voltar");
    private Button buttonSair = new Button("Sair");

    private TextArea textAreaDetalhes = new TextArea();

    private Label labelTitulo = new Label("Recibo da compra");

    private ReciboControl reciboControl = new ReciboControl();

    public ReciboBoundary() {
        pane.getChildren().addAll(buttonVoltar, buttonSair, textAreaDetalhes, labelTitulo);

        labelTitulo.relocate(190, 56);
        labelTitulo.setFont(Font.font(20));

        buttonVoltar.relocate(91, 317);
        buttonVoltar.setPrefSize(105, 27);
        buttonVoltar.setOnAction(e -> this.acionarComando("voltar para catalogo"));

        buttonSair.relocate(333, 317);
        buttonSair.setPrefSize(105, 27);
        buttonSair.setOnAction(e -> this.acionarComando("sair"));

        textAreaDetalhes.relocate(91, 106);
        textAreaDetalhes.setPrefSize(347, 188);
        textAreaDetalhes.editableProperty().setValue(false);
        textAreaDetalhes.setWrapText(true);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada, Endereco enderecoEntrega) {
        reciboGerado.setCompra(compraRealizada);
        this.enderecoEntrega = enderecoEntrega;
        try {
            reciboGerado = reciboControl.salvarRecibo(reciboGerado);
        } catch (ReciboException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro ao salvar recibo!");
            alert.showAndWait();
            e.printStackTrace();
        }
        carregarDetalhes();
        return pane;
    }

    private void carregarDetalhes() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        textAreaDetalhes.setText("Código da compra: " + reciboGerado.getCompra().getId() +
                "\nEndereço de entrega: " + enderecoEntrega.getRua() + ", número " + enderecoEntrega.getNumero() +
                ", " + enderecoEntrega.getComplemento() +
                "\nProduto comprado: " + reciboGerado.getCompra().getRoupa().getModelo() +
                "\nData e hora da compra: " + fmt.format(reciboGerado.getData()) +
                "\nQuantidade de itens comprados: " + reciboGerado.getCompra().getQuantidade() +
                "\nForma de pagamento: " + reciboGerado.getCompra().getPagamento().getNome() +
                "\nValor total: " + NumberFormat.getCurrencyInstance().format(reciboGerado.getCompra().getTotal()));
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
