package dao.impl;

import dao.CompraDAO;
import dao.exceptions.CompraException;
import model.entity.Compra;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompraDAOImpl implements CompraDAO {

    @Override
    public Compra salvarCompra(Compra compra) throws CompraException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO compras (usuario_id, roupa_id, pagamento_id, quantidade, total) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, compra.getUsuario().getId());
            st.setLong(2,  compra.getRoupa().getId());
            st.setLong(3, compra.getPagamento().getId());
            st.setInt(4, compra.getQuantidade());
            st.setDouble(5, compra.getTotal());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            compra.setId(rs.getLong(1));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CompraException(e);
        }
        return compra;
    }
}
