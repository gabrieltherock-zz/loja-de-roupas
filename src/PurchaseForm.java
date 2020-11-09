import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PurchaseForm extends Application{

	private Label labelItem = new Label("Item");
	private Label labelQuantidade = new Label("Quantidade");
	private Label labelTotal = new Label("Total");
    private Label labelFormadePagamento = new Label("Forma de Pagamento");
    private Label labelModeloRoupa = new Label("Camisa Polo");
    private Label labelValor = new Label("R$ 59,90");
    
    private Spinner<String> spinnerQuantidade = new Spinner<>(1, Integer.MAX_VALUE, 1);
    private ComboBox<String> comboBoxFormadePagamento = new ComboBox<>();
    
    private Button buttonComprar = new Button("Comprar");
    
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 600, 420);
        
        stage.setScene(scene);
        stage.setTitle("Compra");
        stage.show();

        pane.getChildren().addAll(labelItem, labelQuantidade, labelTotal, labelFormadePagamento,
        		labelModeloRoupa, labelValor, spinnerQuantidade, comboBoxFormadePagamento, buttonComprar);
        
        //Item
        labelItem.relocate(20,20);
        labelModeloRoupa.setStyle("-fx-font-size:40");
        labelModeloRoupa.relocate(20, 30); 
        
        //Quantidade
        labelQuantidade.relocate(320,50);
       	spinnerQuantidade.relocate(470, 50);
       	spinnerQuantidade.setMinWidth(110);
       	spinnerQuantidade.setMaxWidth(110);
       	spinnerQuantidade.setEditable(true);
        
        //Total
        labelTotal.relocate(20,150);
        labelValor.setStyle("-fx-font-size:40");
        labelValor.relocate(20, 165); 
               
        //Pagamento
        labelFormadePagamento.relocate(320, 150);
        comboBoxFormadePagamento.relocate(470, 150);
        comboBoxFormadePagamento.setMinWidth(110);
        comboBoxFormadePagamento.setMaxWidth(110);
        comboBoxFormadePagamento.getItems().addAll("Boleto", "Débito", "Crédito");
                
        buttonComprar.setMinSize(220, 15);
        buttonComprar.setStyle("-fx-font-size:20");
        buttonComprar.relocate(200, 320);

    }

    public static void main(String[] args) {
        Application.launch(PurchaseForm.class, args);
    }
}
