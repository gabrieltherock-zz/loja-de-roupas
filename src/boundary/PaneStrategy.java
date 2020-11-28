package boundary;

import javafx.scene.layout.Pane;
import model.entity.Usuario;

public interface PaneStrategy {

    public Pane getPane(Usuario usuarioLogado, Integer roupaSelecionadaId);
}
