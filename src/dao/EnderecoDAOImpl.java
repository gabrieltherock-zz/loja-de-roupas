package dao;

import dao.exceptions.EnderecoException;
import model.entity.Endereco;
import singleton.ConnectionSingleton;

import java.sql.*;

public class EnderecoDAOImpl implements EnderecoDAO {

    @Override
    public Endereco adicionar(Endereco e) throws EnderecoException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO enderecos (usuario_id, cep, rua, numero, complemento, referencia) " +
                    "VALUES ((select usuario_id from usuarios where usuario_id = (select max(usuario_id) from usuarios))," +
                    "?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,  e.getCep());
            st.setString(2, e.getRua());
            st.setLong(3, e.getNumero());
            st.setString(4, e.getComplemento());
            st.setString(5, e.getReferencia());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            e.setId(rs.getLong(1));
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new EnderecoException(ex);
        }
        return e;
    }
}
