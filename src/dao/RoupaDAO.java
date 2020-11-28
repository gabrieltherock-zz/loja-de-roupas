package dao;

import dao.exceptions.RoupaException;
import boundary.view.RoupasView;
import model.entity.Roupa;

import java.util.List;

public interface RoupaDAO {

    List<RoupasView> carregarProdutos() throws RoupaException;

    Roupa retornarRoupa(Roupa roupa) throws RoupaException;

    Roupa atualizarRoupa(Roupa roupa) throws RoupaException;
}
