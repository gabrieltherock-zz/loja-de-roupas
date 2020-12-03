package boundary;

import boundary.view.RoupasView;
import control.RoupaControl;
import dao.exceptions.RoupaException;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.entity.Compra;
import model.entity.Endereco;
import model.entity.Roupa;
import model.entity.Usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CatalogoBoundary implements PaneStrategy, ProdutorComando {

    private Usuario usuarioLogado = new Usuario();

    private Roupa roupaSelecionada = new Roupa();

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelInfo = new Label("Selecione um produto e clique no ícone abaixo para mostrar detalhes");
    private Label labelTitulo = new Label("Catálogo de produtos");
    private Button buttonComprar = new Button("Comprar");
    private Button buttonCadastrarOutro = new Button("Cadastrar outro");
    private Button buttonRemover = new Button("Excluir");

    private TableView<RoupasView> tableViewProducts = new TableView();
    private TableColumn tableColumnModelo = new TableColumn("Modelo");
    private TableColumn tableColumnMarca = new TableColumn("Marca");
    private TableColumn tableColumnPreco = new TableColumn("Preço");

    private Image image = new Image(new FileInputStream(System.getProperty("user.dir") +"/images/info-icon.png"), 48, 48, false , false);
    private ImageView imageView = new ImageView(image);

    RoupaControl roupaControl = new RoupaControl();

    List<RoupasView> roupas;

    public CatalogoBoundary() throws FileNotFoundException {

        pane.getChildren().addAll(labelInfo, labelTitulo, tableViewProducts, imageView);

        labelInfo.relocate(65, 232);
        labelInfo.setStyle("-fx-font-size:13; -fx-text-fill:white;");

        labelTitulo.relocate(176, 50);
        labelTitulo.setStyle("-fx-font-size:20; -fx-text-fill:white;");

        imageView.relocate(115, 269);
        imageView.resize(48, 48);
        imageView.setOnMouseClicked(e -> this.acionarComando("detalhes"));

        buttonComprar.relocate(223, 264);
        buttonComprar.setPrefSize(192, 58);
        buttonComprar.setOnAction(e -> this.acionarComando("comprar"));
        buttonComprar.setStyle("-fx-background-color: white");

        buttonRemover.relocate(78, 264);
        buttonRemover.setPrefSize(116, 58);
        buttonRemover.setStyle("-fx-background-color: white");
        buttonRemover.setOnAction(e -> deletarRoupa());

        buttonCadastrarOutro.relocate(306, 264);
        buttonCadastrarOutro.setPrefSize(156, 58);
        buttonCadastrarOutro.setStyle("-fx-background-color: white");
        buttonCadastrarOutro.setOnAction(e -> this.acionarComando("acessar cadastro de produto"));

        tableViewProducts.setEditable(false);
        tableViewProducts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewProducts.relocate(60, 98);
        tableViewProducts.setPrefSize(410, 126);
        tableViewProducts.getColumns().addAll(tableColumnModelo, tableColumnMarca, tableColumnPreco);
        tableViewProducts.setStyle("-fx-background-color: white");

        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<RoupasView, String>("modelo"));
        tableColumnMarca.setCellValueFactory(new PropertyValueFactory<RoupasView, String>("marca"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<RoupasView, String>("preco"));
    }

    private void deletarRoupa() {
        roupaSelecionada.setId(tableViewProducts.getSelectionModel().getSelectedItem().getId());
        try {
            long idDeletado = roupaControl.deletarRoupa(roupaSelecionada);
            tableViewProducts.getItems().remove(tableViewProducts.getSelectionModel().getSelectedIndex());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Deu tudo certo!");
            alert.setContentText("A roupa de id " + idDeletado + " foi excluída com sucesso!");
            alert.showAndWait();
        } catch (RoupaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro ao excluir a roupa!");
            alert.showAndWait();
            e.printStackTrace();
            e.printStackTrace();
        }

    }

    @Override
    public Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada, Endereco enderecoEntrega) {
        this.usuarioLogado = usuarioLogado;

        if (this.usuarioLogado.getEmail().equals("admin@admin.com")) {
            pane.getChildren().remove(buttonRemover);
            pane.getChildren().add(buttonRemover);
            pane.getChildren().remove(buttonCadastrarOutro);
            pane.getChildren().add(buttonCadastrarOutro);
            imageView.relocate(226, 269);
        } else {
            pane.getChildren().remove(buttonComprar);
            pane.getChildren().add(buttonComprar);
        }

        try {
            roupas = roupaControl.carregarRoupasView();
        } catch (RoupaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro ao carregar roupas!");
            alert.showAndWait();
            e.printStackTrace();
        }

        tableViewProducts.setItems(FXCollections.observableArrayList(roupas));
        return pane;
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
        if (tableViewProducts.getSelectionModel().getSelectedItem() != null) {
            roupaSelecionada.setId(tableViewProducts.getSelectionModel().getSelectedItem().getId());
            try {
                roupaSelecionada = roupaControl.carregarRoupa(roupaSelecionada);
                LoginBoundary.setRoupaSelecionada(roupaSelecionada);
            } catch (RoupaException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro!");
                alert.setContentText("Erro ao selecionar roupa!");
                alert.showAndWait();
                e.printStackTrace();
            }
            this.assinanteComando.executarComando(comando);
        }
    }
}
