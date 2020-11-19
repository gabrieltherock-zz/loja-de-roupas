package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroBoundary extends Application{

	private Label labelNome = new Label("Nome");
	private Label labelCpf = new Label("CPF");
	private Label labelEmail = new Label("E-mail");
    private Label labelSenha = new Label("Senha");
    private Label labelTelefone = new Label("Telefone");
    private Label labelCep = new Label("CEP");
    private Label labelLogradouro = new Label("Logradouro");
    private Label labelRua = new Label("Rua");
    private Label labelReferencia = new Label("Ponto de referência");
    
    private TextField textFieldNome = new TextField();
    private TextField textFieldCpf = new TextField();
    private TextField textFieldEmail = new TextField();
    private TextField textFieldSenha = new TextField();
    private TextField textFieldTelefone = new TextField();
    private TextField textFieldCep = new TextField();
    private TextField textFieldLogradouro = new TextField();
    private TextField textFieldRua = new TextField();
    private TextField textFieldReferencia = new TextField();
    
    private Button buttonCadastrarUsuario = new Button("Cadastrar");
    

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 600, 420);
        
        stage.setScene(scene);
        stage.setTitle("Cadastro");
        stage.show();

        pane.getChildren().addAll(labelNome, labelCpf, labelEmail, labelSenha,
        		labelTelefone, labelCep, labelLogradouro, labelRua, labelReferencia,
                textFieldNome, textFieldCpf, textFieldEmail, textFieldSenha,
                textFieldTelefone, textFieldCep, textFieldLogradouro, textFieldRua, textFieldReferencia,
                buttonCadastrarUsuario);
        
        //1 Nome
        labelNome.relocate(10,20);
        textFieldNome.setMinSize(202, 24);
        textFieldNome.relocate(100,20);
        
        //2 CPF
        labelCpf.relocate(330,20);
        textFieldCpf.setMinSize(202, 24);
        textFieldCpf.relocate(385, 20);
        
        //3 Email
        labelEmail.relocate(10,70);
        textFieldEmail.setMinSize(202, 24);
        textFieldEmail.relocate(100,70);
        
        //4 Senha
        labelSenha.relocate(330, 70);
        textFieldSenha.setMinSize(202, 24);
        textFieldSenha.relocate(385, 70);

        //5 Telefone
        labelTelefone.relocate(10,120);
        textFieldTelefone.setMinSize(202, 24);
        textFieldTelefone.relocate(100,120);
        
        //6 CEP
        labelCep.relocate(330,120);
        textFieldCep.setMinSize(202, 24);
        textFieldCep.relocate(385,120);

        //7 Logradouro
        labelLogradouro.relocate(10,170);
        textFieldLogradouro.setMinSize(202, 24);
        textFieldLogradouro.relocate(100,170);
        
        //8 Rua
        labelRua.relocate(330,170);
        textFieldRua.setMinSize(202, 24);
        textFieldRua.relocate(385,170);
        
        //9 Ponto de referencia
        labelReferencia.relocate(10,220);
        textFieldReferencia.setMinSize(435, 24);
        textFieldReferencia.relocate(153,220);
        
        buttonCadastrarUsuario.setMinSize(200, 103);
        buttonCadastrarUsuario.setStyle("-fx-font-size:30");
        buttonCadastrarUsuario.relocate(385, 280);

    }

    public static void main(String[] args) {
        Application.launch(CadastroBoundary.class, args);
    }
}

