package boundary;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.entity.Roupa;
import model.entity.Usuario;

public class DetalhesBoundary implements PaneStrategy, ProdutorComando {

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
        textAreaDetalhes.wrapTextProperty().setValue(true);
        textAreaDetalhes.setText("Descrição do produto\n\n" +
                "Modelo: Camisa Polo\n" +
                "Marca: Polo\n" +
                "Sexo: Feminino\n" +
                "Cor: Azul\n" +
                "Tamanho: G\n" +
                "Tecido: Algodão\n" +
                "Descrição: Uma camisa elegante, bonita e adequada para o dia a dia e, também, para ocasiões especiais.");
    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada) {
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
