package boundary;

import control.ReciboControl;
import dao.exceptions.ReciboException;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.entity.Compra;
import model.entity.Recibo;
import model.entity.Roupa;
import model.entity.Usuario;

import java.text.DecimalFormat;

public class ReciboBoundary implements PaneStrategy, ProdutorComando {

    private Recibo reciboGerado = new Recibo();

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Button buttonVoltar = new Button("Voltar");

    private TextArea textAreaDetalhes = new TextArea();

    private ReciboControl reciboControl = new ReciboControl();

    public ReciboBoundary() {
        pane.getChildren().addAll(buttonVoltar, textAreaDetalhes);

        buttonVoltar.relocate(212, 317);
        buttonVoltar.setPrefSize(105, 27);
        buttonVoltar.setOnAction(e -> this.acionarComando("voltar para compra"));

        textAreaDetalhes.relocate(91, 106);
        textAreaDetalhes.setPrefSize(347, 188);
        textAreaDetalhes.editableProperty().setValue(false);
        textAreaDetalhes.setWrapText(true);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada) {
        reciboGerado.setCompra(compraRealizada);
        try {
            reciboGerado = reciboControl.salvarRecibo(reciboGerado);
        } catch (ReciboException e) {
            e.printStackTrace();
        }
        carregarDetalhes();
        return pane;
    }

    private void carregarDetalhes() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        textAreaDetalhes.setText("                    Recibo da compra\n" +
                "\nCódigo da compra: " + reciboGerado.getCompra().getId() +
                "\nProduto comprado: " + reciboGerado.getCompra().getRoupa().getModelo() +
                "\nData e hora da compra: " + reciboGerado.getData() +
                "\nQuantidade de itens comprados: " + reciboGerado.getCompra().getQuantidade() + " unidades" +
                "\nForma de pagamento: " + reciboGerado.getCompra().getPagamento().getNome() +
                "\nValor total: R$ " + df.format(reciboGerado.getCompra().getTotal()));
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
