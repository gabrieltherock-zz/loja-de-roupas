package boundary;

import javafx.scene.layout.Pane;
import model.entity.Compra;
import model.entity.Roupa;
import model.entity.Usuario;

public interface PaneStrategy {

    Pane getPane(Usuario usuarioLogado, Roupa roupaSelecionada, Compra compraRealizada);
}
