package boundary;

import controller.DatabaseController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginBoundary extends Application {

    private Label labelEmail = new Label("E-mail");
    private Label labelSenha = new Label("Senha");

    private TextField textFieldEmail = new TextField();
    private TextField textFieldSenha = new TextField();

    private Button buttonCadastrar = new Button("Cadastrar");
    private Button buttonAcessar = new Button("Acessar");

    @Override
    public void start(Stage stage) throws Exception {

        DatabaseController dc = new DatabaseController();

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 469, 350);

        pane.getChildren().addAll(labelEmail, labelSenha,
                textFieldEmail, textFieldSenha,
                buttonCadastrar, buttonAcessar);

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

        stage.setScene(scene);
        stage.setTitle("Loja de Roupas");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(LoginBoundary.class, args);
    }
}
