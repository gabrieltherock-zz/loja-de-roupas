package dao.impl;

import dao.ReciboDAO;
import dao.exceptions.ReciboException;
import model.entity.Recibo;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ReciboDAOImpl implements ReciboDAO {

    @Override
    public Recibo salvarRecibo(Recibo recibo) throws ReciboException {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO recibos (compra_id, data_compra) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, recibo.getCompra().getId());
            st.setTimestamp(2, timestamp);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            recibo.setId(rs.getLong(1));
            recibo.setData(timestamp);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ReciboException(e);
        }
        return recibo;
    }
}
