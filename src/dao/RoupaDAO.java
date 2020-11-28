package dao;

import dao.exceptions.RoupaException;
import boundary.view.RoupasView;

import java.util.List;

public interface RoupaDAO {

    List<RoupasView> carregarProdutos() throws RoupaException;
}
