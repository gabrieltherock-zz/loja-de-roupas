package control;

import dao.CompraDAO;
import dao.exceptions.CompraException;
import dao.impl.CompraDAOImpl;
import model.entity.Compra;

public class CompraControl {

    CompraDAO compraDAO = new CompraDAOImpl();

    public Compra realizarCompra(Compra compra) throws CompraException {
        return compraDAO.salvarCompra(compra);
    }
}
