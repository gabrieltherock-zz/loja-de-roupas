package dao;

import dao.exceptions.LoginException;
import model.entity.Usuario;
import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements  LoginDAO {

    @Override
    public Usuario verificaLogin(Usuario usuario) throws LoginException {
        try {
            Connection con = ConnectionSingleton.instancia().connection();
            String sql = "SELECT * FROM usuarios WHERE email='" + usuario.getEmail() +
                    "' AND senha='" + usuario.getSenha() + "'";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.first();
            usuario.setId(rs.getLong(1));
            usuario.setNome(rs.getString(2));
            usuario.setCpf(rs.getString(3));
            usuario.setTelefone(rs.getString(6));
            con.close();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException(e);
        }
    }
}
