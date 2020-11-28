package boundary;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.entity.Compra;
import model.entity.Roupa;
import model.entity.Usuario;

public class DetalhesBoundary implements PaneStrategy, ProdutorComando {

    private Roupa roupaSelecionada;

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Button buttonVoltar = new Button("Voltar");

    private TextArea textAreaDetalhes = new TextArea();

    public DetalhesBoundary() {
        pane.getChildren().addAll(buttonVoltar, textAreaDetalhes);

        buttonVoltar.relocate(212, 317);
        buttonVoltar.setPrefSize(105, 27);
        buttonVoltar.setOnAction(e -> this.acionarComando("voltar"));

        textAreaDetalhes.relocate(91, 106);
        textAreaDetalhes.setPrefSize(347, 188);
        textAreaDetalhes.editableProperty().setValue(false);
        textAreaDetalhes.setWrapText(true);
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada) {
        this.roupaSelecionada = roupaSelecionada;
        carregarDetalhes();
        return pane;
    }

    private void carregarDetalhes() {
        textAreaDetalhes.setText("                    Descrição do produto\n\n" +
                "Modelo: " + roupaSelecionada.getModelo() +
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
