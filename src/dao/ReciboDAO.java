package dao;

import dao.exceptions.ReciboException;
import model.entity.Recibo;

public interface ReciboDAO {

    Recibo salvarRecibo(Recibo recibo) throws ReciboException;
}
