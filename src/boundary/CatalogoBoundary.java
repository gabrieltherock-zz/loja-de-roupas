package boundary;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.entity.Produto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CatalogoBoundary implements PaneStrategy, ProdutorComando {

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Label labelInfo = new Label("Selecione um produto e clique no ícone abaixo para mostrar detalhes");

    private Button buttonComprar = new Button("Comprar");

    private TableView<Produto> tableViewProducts = new TableView();
    private TableColumn tableColumnModelo = new TableColumn("Modelo");
    private TableColumn tableColumnMarca = new TableColumn("Marca");
    private TableColumn tableColumnPreco = new TableColumn("Preco");

    private Image image = new Image(new FileInputStream(System.getProperty("user.dir") +"/images/info-icon.png"), 48, 48, false , false);
    private ImageView imageView = new ImageView(image);

    public CatalogoBoundary() throws FileNotFoundException {
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

        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<Produto, String>("modelo"));
        tableColumnMarca.setCellValueFactory(new PropertyValueFactory<Produto, String>("marca"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Produto, String>("preco"));

        tableViewProducts.setItems(FXCollections.observableArrayList(
                new Produto("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Produto("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Produto("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Produto("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Produto("Camisa Arco íris", "Gabriel's", "R$ 53,99")
        ));
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
