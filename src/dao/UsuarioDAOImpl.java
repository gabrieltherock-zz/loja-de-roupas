package dao;

import dao.exceptions.UsuarioException;
import model.entity.Usuario;
import singleton.ConnectionSingleton;

import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {

    public UsuarioDAOImpl() {}

    @Override
    public Usuario adicionar(Usuario u) throws UsuarioException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO usuarios (nome, cpf, email, senha, telefone) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, u.getNome());
            st.setString(2,  u.getCpf());
            st.setString(3, u.getEmail());
            st.setString(4, u.getSenha());
            st.setString(5, u.getTelefone());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            u.setId(rs.getLong(1));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException(e);
        }
        return u;
    }
}
