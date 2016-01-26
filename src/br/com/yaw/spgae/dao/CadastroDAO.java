package br.com.yaw.spgae.dao;

import java.util.List;

import br.com.yaw.spgae.model.Cadastro;

public interface CadastroDAO {

	Cadastro save(Cadastro cadastro);
	List<Cadastro> getAll();
	Boolean remove(Cadastro cadastro);
	Cadastro findById(Long id);
}
