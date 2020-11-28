package dao.exceptions;

public class ProdutoException extends Exception {

    public ProdutoException() {
    }

    public ProdutoException(String arg0) {
        super(arg0);
    }

    public ProdutoException(Throwable arg0) {
        super(arg0);
    }

    public ProdutoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ProdutoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
