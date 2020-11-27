package control;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import dao.exceptions.UsuarioException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entity.Usuario;

public class UsuarioControl {

    private StringProperty nomeProperty = new SimpleStringProperty("Antônio");
    private StringProperty cpfProperty = new SimpleStringProperty("63268752989");
    private StringProperty emailProperty = new SimpleStringProperty("antonio@gmail.com");
    private StringProperty senhaProperty = new SimpleStringProperty("123456");
    private StringProperty telefoneProperty = new SimpleStringProperty("11965744387");

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public Usuario getUsuario() {
        Usuario u = new Usuario();
        u.setCpf(cpfProperty.get());
        u.setNome(nomeProperty.get());
        u.setEmail(emailProperty.get());
        u.setSenha(senhaProperty.get());
        u.setTelefone(telefoneProperty.get());
        return u;
    }

    public void setUsuario(Usuario u) {
        if (u != null) {
            cpfProperty.set(u.getCpf());
            nomeProperty.set(u.getNome());
            emailProperty.set(u.getEmail());
            senhaProperty.set(u.getSenha());
            telefoneProperty.set(u.getTelefone());
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
