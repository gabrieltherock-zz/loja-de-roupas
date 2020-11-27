package dao;

import dao.exceptions.UsuarioException;
import model.entity.Usuario;

public interface UsuarioDAO {

    Usuario adicionar(Usuario usuario) throws UsuarioException;
}
