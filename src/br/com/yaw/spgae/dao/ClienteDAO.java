package br.com.yaw.spgae.dao;

import java.util.List;

import br.com.yaw.spgae.model.Cliente;

public interface ClienteDAO {

	Cliente save(Cliente cliente);
	List<Cliente> getAll();
	Boolean remove(Cliente cliente);
	Cliente findById(Long id);
}
