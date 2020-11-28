package dao.exceptions;

public class CompraException extends Exception {

    public CompraException() {
    }

    public CompraException(String arg0) {
        super(arg0);
    }

    public CompraException(Throwable arg0) {
        super(arg0);
    }

    public CompraException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public CompraException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
