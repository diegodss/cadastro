package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.googlecode.objectify.Key;
import br.com.yaw.spgae.model.Cadastro;

@Repository
public class CadastroDAOObjectify implements Serializable, CadastroDAO {

	@Override
	public Cadastro save(Cadastro cadastro) {
		ofy().save().entity(cadastro).now();
		return cadastro;
	}
	
	@Override
	public List<Cadastro> getAll(){
		return ofy().load().type(Cadastro.class).list();		
	}
	
	@Override
	public Boolean remove(Cadastro cadastro) {
		ofy().delete().entity(cadastro).now();
		return true;
	}
	@Override
	public Cadastro findById(Long id) {
		Key<Cadastro> k = Key.create(Cadastro.class, id);
		return ofy().load().key(k).get();
	}
}
