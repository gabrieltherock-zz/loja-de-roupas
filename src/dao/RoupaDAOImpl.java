package dao;

import boundary.view.RoupasView;
import dao.exceptions.RoupaException;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoupaDAOImpl implements RoupaDAO {

    @Override
    public List<RoupasView> carregarProdutos() throws RoupaException {
        List<RoupasView> roupas = new ArrayList<>();
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "SELECT modelo, marca, valor FROM roupas";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RoupasView roupaControl = new RoupasView(rs.getString(1),
                        rs.getString(2),
                        NumberFormat.getCurrencyInstance().format(rs.getDouble(3)));
                roupas.add(roupaControl);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoupaException(e);
        }
        return roupas;
    }
}
