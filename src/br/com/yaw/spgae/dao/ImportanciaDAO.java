package br.com.yaw.spgae.dao;

import java.util.List;

import br.com.yaw.spgae.model.Importancia;

public interface ImportanciaDAO {

	Importancia save(Importancia Importancia);
	List<Importancia> getAll();
	Boolean remove(Importancia Importancia);
	Importancia findById(Long id);
}
