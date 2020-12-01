package control;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import dao.exceptions.UsuarioException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entity.Usuario;

public class UsuarioControl {

    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty cpfProperty = new SimpleStringProperty();
    private StringProperty emailProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();
    private StringProperty telefoneProperty = new SimpleStringProperty();

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpfProperty.get());
        usuario.setNome(nomeProperty.get());
        usuario.setEmail(emailProperty.get());
        usuario.setSenha(senhaProperty.get());
        usuario.setTelefone(telefoneProperty.get());
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario != null) {
            cpfProperty.set(usuario.getCpf());
            nomeProperty.set(usuario.getNome());
            emailProperty.set(usuario.getEmail());
            senhaProperty.set(usuario.getSenha());
            telefoneProperty.set(usuario.getTelefone());
        }
    }

    public Usuario adicionar() throws UsuarioException {
        return usuarioDAO.adicionar(getUsuario());
    }

    public StringProperty getNomeProperty() {
        return nomeProperty;
    }

    public StringProperty getTelefoneProperty() {
        return telefoneProperty;
    }

    public StringProperty getEmailProperty() {
        return emailProperty;
    }

    public StringProperty getCpfProperty() {
        return cpfProperty;
    }

    public StringProperty getSenhaProperty() {
        return senhaProperty;
    }
}
