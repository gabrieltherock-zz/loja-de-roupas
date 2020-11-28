package dao;

import dao.exceptions.EnderecoException;
import model.entity.Endereco;
import model.entity.Usuario;

public interface EnderecoDAO {

    Endereco adicionar(Endereco endereco) throws EnderecoException;

    Endereco encontrarEndereco(Usuario usuario) throws EnderecoException;
}
