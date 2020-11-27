package boundary;

import dao.exceptions.EnderecoException;
import dao.exceptions.UsuarioException;
import control.EnderecoControl;
import control.UsuarioControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.entity.Endereco;
import model.entity.Usuario;

public class CadastroBoundary implements PaneStrategy, ProdutorComando{

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

	private Label labelNome = new Label("Nome");
	private Label labelCpf = new Label("CPF");
	private Label labelEmail = new Label("E-mail");
    private Label labelSenha = new Label("Senha");
    private Label labelTelefone = new Label("Telefone");
    private Label labelCep = new Label("CEP");
    private Label labelNumero = new Label("Número");
    private Label labelComplemento = new Label("Comp.");
    private Label labelRua = new Label("Rua");
    private Label labelReferencia = new Label("Referência");
    
    private TextField textFieldNome = new TextField();
    private TextField textFieldCpf = new TextField();
    private TextField textFieldEmail = new TextField();
    private TextField textFieldSenha = new TextField();
    private TextField textFieldTelefone = new TextField();
    private TextField textFieldCep = new TextField();
    private TextField textFieldNumero = new TextField();
    private TextField textFieldComplemento = new TextField();
    private TextField textFieldRua = new TextField();
    private TextField textFieldReferencia = new TextField();
    
    private Button buttonCadastrarUsuario = new Button("Cadastrar");

    private UsuarioControl usuarioControl = new UsuarioControl();
    private EnderecoControl enderecoControl = new EnderecoControl();

    public void vincularCampos() {
        StringConverter<? extends Number> numeroConverter = new IntegerStringConverter();

        Bindings.bindBidirectional(textFieldNome.textProperty(), usuarioControl.getNomeProperty());
        Bindings.bindBidirectional(textFieldCpf.textProperty(), usuarioControl.getCpfProperty());
        Bindings.bindBidirectional(textFieldEmail.textProperty(), usuarioControl.getEmailProperty());
        Bindings.bindBidirectional(textFieldSenha.textProperty(), usuarioControl.getSenhaProperty());
        Bindings.bindBidirectional(textFieldTelefone.textProperty(), usuarioControl.getTelefoneProperty());

        Bindings.bindBidirectional(textFieldCep.textProperty(), enderecoControl.getCepProperty());
        Bindings.bindBidirectional(	textFieldNumero.textProperty(),
                enderecoControl.getNumeroProperty(),
                (StringConverter<Number>) numeroConverter);
        Bindings.bindBidirectional(textFieldComplemento.textProperty(), enderecoControl.getComplementoProperty());
        Bindings.bindBidirectional(textFieldRua.textProperty(), enderecoControl.getRuaProperty());
        Bindings.bindBidirectional(textFieldReferencia.textProperty(), enderecoControl.getReferenciaProperty());
    }

    public CadastroBoundary() {
        vincularCampos();

        pane.getChildren().addAll(labelNome, labelCpf, labelEmail, labelSenha,
        		labelTelefone, labelCep, labelNumero, labelComplemento, labelRua, labelReferencia,
                textFieldNome, textFieldCpf, textFieldEmail, textFieldSenha,
                textFieldTelefone, textFieldCep, textFieldNumero, textFieldComplemento, textFieldRua, textFieldReferencia,
                buttonCadastrarUsuario);
        
        //1 Nome
        labelNome.relocate(16,55);
        textFieldNome.setMinSize(155, 27);
        textFieldNome.relocate(110,52);
        
        //2 CPF
        labelCpf.relocate(306,55);
        textFieldCpf.setMinSize(155, 27);
        textFieldCpf.relocate(359, 52);
        
        //3 Email
        labelEmail.relocate(16,109);
        textFieldEmail.setMinSize(155, 27);
        textFieldEmail.relocate(110,106);
        
        //4 Senha
        labelSenha.relocate(306, 109);
        textFieldSenha.setMinSize(155, 27);
        textFieldSenha.relocate(359, 106);

        //5 Telefone
        labelTelefone.relocate(16,165);
        textFieldTelefone.setMinSize(155, 27);
        textFieldTelefone.relocate(110,159);
        
        //6 CEP
        labelCep.relocate(306,165);
        textFieldCep.setMinSize(155, 27);
        textFieldCep.relocate(359,159);

        //7 Numero
        labelNumero.relocate(16,217);
        textFieldNumero.setMinSize(155, 27);
        textFieldNumero.relocate(110,214);
        
        //8 Rua
        labelRua.relocate(307,217);
        textFieldRua.setMinSize(155, 27);
        textFieldRua.relocate(359,214);
        
        //9 Ponto de referencia
        labelReferencia.relocate(16,269);
        textFieldReferencia.setMinSize(155, 27);
        textFieldReferencia.relocate(110,266);

        //10 Complemento
        labelComplemento.relocate(306, 269);
        textFieldComplemento.setMinSize(155, 27);
        textFieldComplemento.relocate(359, 266);
        
        buttonCadastrarUsuario.setMinSize(121, 65);
        buttonCadastrarUsuario.setStyle("-fx-font-size:30");
        buttonCadastrarUsuario.relocate(348, 312);
        buttonCadastrarUsuario.setOnAction(e -> acionarComando("acessar"));
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
        try {
            Usuario u = usuarioControl.adicionar();
            Endereco e = enderecoControl.adicionar();
            e.setUsuario(u);
            u.mostrarUsuario();
            e.mostrarEndereco();
        } catch (UsuarioException | EnderecoException e) {
            e.printStackTrace();
        }
        this.assinanteComando.executarComando(comando);
    }

}

