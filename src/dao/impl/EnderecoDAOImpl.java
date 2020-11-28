package dao.impl;

import dao.EnderecoDAO;
import dao.exceptions.EnderecoException;
import dao.exceptions.RoupaException;
import model.entity.Endereco;
import model.entity.Usuario;
import model.enums.Sexo;
import model.enums.Tamanho;
import model.enums.Tecido;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoDAOImpl implements EnderecoDAO {

    @Override
    public Endereco adicionar(Endereco endereco) throws EnderecoException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO enderecos (usuario_id, cep, rua, numero, complemento, referencia) " +
                    "VALUES ((select usuario_id from usuarios where usuario_id = (select max(usuario_id) from usuarios))," +
                    "?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,  endereco.getCep());
            st.setString(2, endereco.getRua());
            st.setLong(3, endereco.getNumero());
            st.setString(4, endereco.getComplemento());
            st.setString(5, endereco.getReferencia());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            endereco.setId(rs.getLong(1));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EnderecoException(e);
        }
        return endereco;
    }

    @Override
    public Endereco encontrarEndereco(Usuario usuario) throws EnderecoException {
        Endereco endereco = new Endereco();
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "SELECT * FROM enderecos WHERE usuario_id='" + usuario.getId() + "'";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.executeQuery();
            rs.first();
            endereco.setUsuario(usuario);
            endereco.setCep(rs.getString(3));
            endereco.setRua(rs.getString(4));
            endereco.setNumero(rs.getInt(5));
            endereco.setComplemento(rs.getString(6));
            endereco.setReferencia(rs.getString(7));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EnderecoException(e);
        }
        return endereco;
    }
}
