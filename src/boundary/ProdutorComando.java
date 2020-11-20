package boundary;

public interface ProdutorComando {

    void setAssinanteComando(AssinanteComando assinanteComando);

    void acionarComando(String comando);
}
