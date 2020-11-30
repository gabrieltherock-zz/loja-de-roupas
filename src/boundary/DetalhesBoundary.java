package boundary;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.entity.Compra;
import model.entity.Endereco;
import model.entity.Roupa;
import model.entity.Usuario;

public class DetalhesBoundary implements PaneStrategy, ProdutorComando {

    private Roupa roupaSelecionada;

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Button buttonVoltar = new Button("Voltar");

    private TextArea textAreaDetalhes = new TextArea();

    private Label labelTitulo = new Label("Detalhes do produto");

    public DetalhesBoundary() {
        pane.getChildren().addAll(buttonVoltar, textAreaDetalhes, labelTitulo);

        labelTitulo.relocate(180, 56);
        labelTitulo.setFont(Font.font(20));

        buttonVoltar.relocate(212, 317);
        buttonVoltar.setPrefSize(105, 27);
        buttonVoltar.setOnAction(e -> this.acionarComando("voltar para catalogo"));

        textAreaDetalhes.relocate(91, 106);
        textAreaDetalhes.setPrefSize(347, 188);
        textAreaDetalhes.editableProperty().setValue(false);
        textAreaDetalhes.setWrapText(true);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada, Endereco enderecoEntrega) {
        this.roupaSelecionada = roupaSelecionada;
        carregarDetalhes();
        return pane;
    }

    private void carregarDetalhes() {
        textAreaDetalhes.setText("Modelo: " + roupaSelecionada.getModelo() +
                "\nMarca: " + roupaSelecionada.getMarca() +
                "\nSexo: " + roupaSelecionada.getSexo().getNome() +
                "\nCor: " + roupaSelecionada.getCor() +
                "\nTamanho: " + roupaSelecionada.getTamanho() +
                "\nTecido: " + roupaSelecionada.getTecido().getNome() +
                "\nDescrição: " + roupaSelecionada.getDescricao());
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
