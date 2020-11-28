package dao;

import dao.exceptions.RoupasException;
import boundary.view.RoupasView;

import java.util.List;

public interface RoupasDAO {

    List<RoupasView> carregarProdutos() throws RoupasException;
}
