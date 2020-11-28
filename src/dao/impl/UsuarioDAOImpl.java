package dao.impl;

import dao.UsuarioDAO;
import dao.exceptions.UsuarioException;
import model.entity.Usuario;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAOImpl implements UsuarioDAO {

    public UsuarioDAOImpl() {}

    @Override
    public Usuario adicionar(Usuario usuario) throws UsuarioException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "INSERT INTO usuarios (nome, cpf, email, senha, telefone) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, usuario.getNome());
            st.setString(2,  usuario.getCpf());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getSenha());
            st.setString(5, usuario.getTelefone());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            usuario.setId(rs.getLong(1));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException(e);
        }
        return usuario;
    }
}
