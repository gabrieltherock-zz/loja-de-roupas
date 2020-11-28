package dao;

import dao.exceptions.ProdutoException;
import model.entity.Produto;

import java.util.List;

public interface ProdutoDAO {

    List<Produto> carregarProdutos() throws ProdutoException;
}
