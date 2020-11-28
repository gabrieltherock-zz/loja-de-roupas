package dao;

import dao.exceptions.CompraException;
import model.entity.Compra;

public interface CompraDAO {

    Compra salvarCompra(Compra compra) throws CompraException;
}
