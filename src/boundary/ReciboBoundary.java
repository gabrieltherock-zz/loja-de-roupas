package boundary;

import control.ReciboControl;
import dao.exceptions.ReciboException;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.entity.Compra;
import model.entity.Endereco;
import model.entity.Recibo;
import model.entity.Roupa;
import model.entity.Usuario;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ReciboBoundary implements PaneStrategy, ProdutorComando {

    private Recibo reciboGerado = new Recibo();

    private Endereco enderecoEntrega = new Endereco();

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Button buttonVoltar = new Button("Voltar");
    private Button buttonSair = new Button("Sair");

    private TextArea textAreaDetalhes = new TextArea();

    private ReciboControl reciboControl = new ReciboControl();

    public ReciboBoundary() {
        pane.getChildren().addAll(buttonVoltar, buttonSair, textAreaDetalhes);

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
            e.printStackTrace();
        }
        carregarDetalhes();
        return pane;
    }

    private void carregarDetalhes() {
        textAreaDetalhes.setText("                    Recibo da compra\n" +
                "\nCódigo da compra: " + reciboGerado.getCompra().getId() +
                "\nEndereço de entrega: " + enderecoEntrega.getRua() + ", número " + enderecoEntrega.getNumero() +
                ", " + enderecoEntrega.getComplemento() +
                "\nProduto comprado: " + reciboGerado.getCompra().getRoupa().getModelo() +
                "\nData e hora da compra: " + reciboGerado.getData() +
                "\nQuantidade de itens comprados: " + reciboGerado.getCompra().getQuantidade() + " unidades" +
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
