package control;

import dao.ReciboDAO;
import dao.exceptions.ReciboException;
import dao.impl.ReciboDAOImpl;
import model.entity.Recibo;

public class ReciboControl {

    ReciboDAO reciboDAO = new ReciboDAOImpl();

    public Recibo salvarRecibo(Recibo recibo) throws ReciboException {
        return reciboDAO.salvarRecibo(recibo);
    }
}
