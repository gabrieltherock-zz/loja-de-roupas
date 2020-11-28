package control;

import boundary.view.RoupasView;
import dao.RoupaDAO;
import dao.impl.RoupaDAOImpl;
import dao.exceptions.RoupaException;
import model.entity.Roupa;

import java.util.List;

public class RoupaControl {

    private RoupaDAO roupaDAO = new RoupaDAOImpl();

    public List<RoupasView> carregarRoupas() throws RoupaException {
        return roupaDAO.carregarProdutos();
    }

    public Roupa encontrarRoupa(Roupa roupa) throws RoupaException {
        return roupaDAO.retornarRoupa(roupa);
    }
}
