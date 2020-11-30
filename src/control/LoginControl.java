package control;

import dao.LoginDAO;
import dao.impl.LoginDAOImpl;
import dao.exceptions.LoginException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entity.Usuario;

public class LoginControl {

    private StringProperty emailProperty = new SimpleStringProperty("gabriel@gmail.com");
    private StringProperty senhaProperty = new SimpleStringProperty("123");

    LoginDAO loginDAO = new LoginDAOImpl();

    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail(emailProperty.get());
        usuario.setSenha(senhaProperty.get());
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        emailProperty.set(usuario.getEmail());
        senhaProperty.set(usuario.getSenha());
    }

    public Usuario verificar() throws LoginException {
        return loginDAO.verificaLogin(getUsuario());
    }

    public StringProperty getEmailProperty() {
        return emailProperty;
    }

    public StringProperty getSenhaProperty() {
        return senhaProperty;
    }
}
