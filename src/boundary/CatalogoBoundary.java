package boundary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entity.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CatalogoBoundary extends Application {

    private Button buttonComprar = new Button("Comprar");

    private TableView<Product> tableViewProducts = new TableView();
    private TableColumn tableColumnModelo = new TableColumn("Modelo");
    private TableColumn tableColumnMarca = new TableColumn("Marca");
    private TableColumn tableColumnPreco = new TableColumn("Preco");

    private Image image = new Image(new FileInputStream(System.getProperty("user.dir") +"/images/info-icon.png"), 48, 48, false , false);
    private ImageView imageView = new ImageView(image);

    public CatalogoBoundary() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 412, 339);

        pane.getChildren().addAll(buttonComprar, tableViewProducts, imageView);

        imageView.relocate(59, 233);
        imageView.resize(48, 48);

        buttonComprar.relocate(176, 228);
        buttonComprar.setPrefSize(192, 58);

        tableViewProducts.setEditable(false);
        tableViewProducts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewProducts.relocate(59, 62);
        tableViewProducts.setPrefSize(309, 126);
        tableViewProducts.getColumns().addAll(tableColumnModelo, tableColumnMarca, tableColumnPreco);

        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<Product, String>("modelo"));
        tableColumnMarca.setCellValueFactory(new PropertyValueFactory<Product, String>("marca"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Product, String>("preco"));

        tableViewProducts.setItems(FXCollections.observableArrayList(
                new Product("Camisa Arco Íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco Íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco Íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco Íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco Íris", "Gabriel's", "R$ 53,99")
        ));

        stage.setScene(scene);
        stage.setTitle("Roupas disponíveis");
        stage.show();
    }
}
