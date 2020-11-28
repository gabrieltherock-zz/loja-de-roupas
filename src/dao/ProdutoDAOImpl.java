package dao;

import dao.exceptions.LoginException;
import dao.exceptions.ProdutoException;
import model.entity.Produto;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public List<Produto> carregarProdutos() throws ProdutoException {
        List<Produto> produtos = new ArrayList<>();
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "SELECT modelo, marca, valor FROM roupas";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString(1),
                        rs.getString(2),
                        "R$ " + rs.getString(3));
                produtos.add(produto);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProdutoException(e);
        }
        return produtos;
    }
}
