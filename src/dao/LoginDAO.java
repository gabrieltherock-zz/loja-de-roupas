package dao;

import dao.exceptions.LoginException;
import model.entity.Usuario;

public interface LoginDAO {

    Usuario verificaLogin(Usuario usuario) throws LoginException;
}
