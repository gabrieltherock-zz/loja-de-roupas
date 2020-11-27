package dao;

import dao.exceptions.EnderecoException;
import model.entity.Endereco;

public interface EnderecoDAO {

    Endereco adicionar(Endereco e) throws EnderecoException;
}
