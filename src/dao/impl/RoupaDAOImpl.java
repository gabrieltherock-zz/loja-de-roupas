package dao.impl;

import boundary.view.RoupasView;
import dao.RoupaDAO;
import dao.exceptions.CompraException;
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
    public List<RoupasView> carregarRoupasView() throws RoupaException {
        List<RoupasView> roupas = new ArrayList<>();
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "SELECT modelo, marca, valor, roupa_id FROM roupas";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RoupasView roupaView = new RoupasView(rs.getString(1),
                        rs.getString(2),
                        NumberFormat.getCurrencyInstance().format(rs.getDouble(3)));
                roupaView.setId(rs.getLong(4));
                roupas.add(roupaView);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoupaException(e);
        }
        return roupas;
    }

    @Override
    public Roupa carregarRoupa(Roupa roupa) throws RoupaException {
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

    @Override
    public Roupa salvarRoupa(Roupa roupa) throws RoupaException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO roupas (tamanho_id, tecido_id, sexo_id, quantidade, marca, modelo, cor, descricao, valor) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, roupa.getTamanho().getId());
            st.setLong(2,  roupa.getTecido().getId());
            st.setLong(3, roupa.getSexo().getId());
            st.setInt(4, roupa.getQuantidade());
            st.setString(5, roupa.getMarca());
            st.setString(6, roupa.getModelo());
            st.setString(7, roupa.getCor());
            st.setString(8, roupa.getDescricao());
            st.setDouble(9, roupa.getValor());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            roupa.setId(rs.getLong(1));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoupaException(e);
        }
        return roupa;
    }

    @Override
    public Roupa atualizarRoupa(Roupa roupa) throws RoupaException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "UPDATE roupas SET quantidade=(quantidade - (SELECT quantidade from compras " +
                    "WHERE compra_id = (SELECT MAX(compra_id) FROM compras))) WHERE roupa_id=" +
                    roupa.getId();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.executeUpdate();
            con.close();
            roupa = carregarRoupa(roupa);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoupaException(e);
        }
        return roupa;
    }

    @Override
    public long deletarRoupa(Roupa roupa) throws RoupaException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "DELETE FROM roupas WHERE roupa_id='" +
                    roupa.getId() + "'";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate(sql);
            con.close();
            return roupa.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoupaException(e);
        }
    }
}
