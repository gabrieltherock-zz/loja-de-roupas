package dao.impl;

import boundary.view.RoupasView;
import dao.RoupaDAO;
import dao.exceptions.RoupaException;
import model.entity.Roupa;
import model.enums.Sexo;
import model.enums.Tamanho;
import model.enums.Tecido;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Roupa retornarRoupa(Roupa roupa) throws RoupaException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "SELECT * FROM roupas WHERE roupa_id='" + roupa.getId() + "'";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.executeQuery();
            rs.first();
            roupa.setTamanho(Tamanho.getById(rs.getInt(2)));
            roupa.setTecido(Tecido.getById(rs.getInt(3)));
            roupa.setSexo(Sexo.getById(rs.getInt(4)));
            roupa.setQuantidade(rs.getInt(5));
            roupa.setMarca(rs.getString(6));
            roupa.setModelo(rs.getString(7));
            roupa.setCor(rs.getString(8));
            roupa.setDescricao(rs.getString(9));
            roupa.setValor(rs.getDouble(10));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoupaException(e);
        }
        return roupa;
    }
}
