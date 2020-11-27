package dao.exceptions;

public class UsuarioException extends Exception {

    public UsuarioException() {
    }

    public UsuarioException(String arg0) {
        super(arg0);
    }

    public UsuarioException(Throwable arg0) {
        super(arg0);
    }

    public UsuarioException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public UsuarioException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
