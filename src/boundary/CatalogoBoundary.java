package boundary;

import boundary.view.RoupasView;
import dao.RoupasDAO;
import dao.RoupasDAOImpl;
import dao.exceptions.RoupasException;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CatalogoBoundary implements PaneStrategy, ProdutorComando {

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelInfo = new Label("Selecione um produto e clique no ícone abaixo para mostrar detalhes");

    private Button buttonComprar = new Button("Comprar");

    private TableView<RoupasView> tableViewProducts = new TableView();
    private TableColumn tableColumnModelo = new TableColumn("Modelo");
    private TableColumn tableColumnMarca = new TableColumn("Marca");
    private TableColumn tableColumnPreco = new TableColumn("Preço");

    private Image image = new Image(new FileInputStream(System.getProperty("user.dir") +"/images/info-icon.png"), 48, 48, false , false);
    private ImageView imageView = new ImageView(image);

    RoupasDAO roupasViewDAO = new RoupasDAOImpl();

    List<RoupasView> roupas;

    public void carregaProdutos() {
        try {
            roupas = roupasViewDAO.carregarProdutos();
        } catch (RoupasException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao carregar a lista de roupaControls!").show();
            e.printStackTrace();
        }
    }

    public CatalogoBoundary() throws FileNotFoundException {
        carregaProdutos();

        pane.getChildren().addAll(labelInfo, buttonComprar, tableViewProducts, imageView);

        labelInfo.relocate(100, 232);
        labelInfo.setFont(Font.font(11));

        imageView.relocate(115, 269);
        imageView.resize(48, 48);
        imageView.setOnMouseClicked(e -> this.acionarComando("detalhes"));

        buttonComprar.relocate(223, 264);
        buttonComprar.setPrefSize(192, 58);
        buttonComprar.setOnAction(e -> this.acionarComando("comprar"));

        tableViewProducts.setEditable(false);
        tableViewProducts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewProducts.relocate(115, 98);
        tableViewProducts.setPrefSize(309, 126);
        tableViewProducts.getColumns().addAll(tableColumnModelo, tableColumnMarca, tableColumnPreco);

        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<RoupasView, String>("modelo"));
        tableColumnMarca.setCellValueFactory(new PropertyValueFactory<RoupasView, String>("marca"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<RoupasView, String>("preco"));

        tableViewProducts.setItems(FXCollections.observableArrayList(roupas));

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
        this.assinanteComando.executarComando(comando);
    }
}
