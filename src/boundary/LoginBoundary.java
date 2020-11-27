package boundary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginBoundary extends Application implements EventHandler<ActionEvent>, AssinanteComando {

    private Pane pane = new Pane();

    private CadastroBoundary cadastroBoundary = new CadastroBoundary();
    private CatalogoBoundary catalogoBoundary = new CatalogoBoundary();
    private CompraBoundary compraBoundary = new CompraBoundary();
    private DetalhesBoundary detalhesBoundary = new DetalhesBoundary();

    private PaneStrategy paneStrategy = cadastroBoundary;

    private Label labelEmail = new Label("E-mail");
    private Label labelSenha = new Label("Senha");

    private TextField textFieldEmail = new TextField();
    private TextField textFieldSenha = new TextField();

    private Button buttonCadastrar = new Button("Cadastrar");
    private Button buttonAcessar = new Button("Acessar");

    private Image image = new Image(new FileInputStream(System.getProperty("user.dir") +"/images/key-icon.png"), 128, 128, false , false);
    private ImageView imageView = new ImageView(image);

    public LoginBoundary() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(pane, 530, 400);

        pane.getChildren().addAll(labelEmail, labelSenha,
                textFieldEmail, textFieldSenha,
                buttonCadastrar, buttonAcessar, imageView);

        imageView.relocate(39, 55);

        labelEmail.relocate(203,70);
        textFieldEmail.setMinSize(202, 24);
        textFieldEmail.relocate(254,70);

        labelSenha.relocate(203,124);
        textFieldSenha.setMinSize(202, 24);
        textFieldSenha.relocate(254, 124);

        buttonCadastrar.setMinSize(176, 81);
        buttonCadastrar.setStyle("-fx-font-size:30");
        buttonCadastrar.relocate(52, 226);

        buttonAcessar.setMinSize(176, 81);
        buttonAcessar.setStyle("-fx-font-size:30");
        buttonAcessar.relocate(283, 226);

        buttonCadastrar.setOnAction(this);
        buttonAcessar.setOnAction(this);

        cadastroBoundary.setAssinanteComando(this);
        catalogoBoundary.setAssinanteComando(this);
        compraBoundary.setAssinanteComando(this);
        detalhesBoundary.setAssinanteComando(this);

        stage.setScene(scene);
        stage.setTitle("Loja de Roupas");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(LoginBoundary.class, args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getTarget() == buttonCadastrar)
            this.executarComando("cadastrar");
        else if (event.getTarget() == buttonAcessar)
            this.executarComando("acessar");
    }

    @Override
    public void executarComando(String comando) {
        if ("cadastrar".equals(comando))
            paneStrategy = cadastroBoundary;
        else if ("acessar".equals(comando))
            paneStrategy = catalogoBoundary;
        else if ("comprar".equals(comando))
            paneStrategy = compraBoundary;
        else if ("detalhes".equals(comando))
            paneStrategy = detalhesBoundary;
        else if ("voltar".equals(comando))
            paneStrategy = catalogoBoundary;
        this.paneContext();
    }

    private void paneContext() {
        pane.getChildren().clear();
        pane.getChildren().add(paneStrategy.getPane());
    }

}
