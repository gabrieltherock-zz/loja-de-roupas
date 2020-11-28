package control;

import boundary.view.RoupasView;
import dao.RoupaDAO;
import dao.RoupaDAOImpl;
import dao.exceptions.RoupaException;

import java.util.List;

public class RoupaControl {

    private RoupaDAO roupaDAO = new RoupaDAOImpl();

    public List<RoupasView> carregarRoupas() throws RoupaException {
        return roupaDAO.carregarProdutos();
    }
}
