package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.googlecode.objectify.Key;
import br.com.yaw.spgae.model.Cliente;

@Repository
public class ClienteDAOObjectify implements Serializable, ClienteDAO {

	@Override
	public Cliente save(Cliente cliente) {
		ofy().save().entity(cliente).now();
		return cliente;
	}
	
	@Override
	public List<Cliente> getAll(){
		return ofy().load().type(Cliente.class).list();		
	}
	
	@Override
	public Boolean remove(Cliente cliente) {
		ofy().delete().entity(cliente).now();
		return true;
	}
	@Override
	public Cliente findById(Long id) {
		Key<Cliente> k = Key.create(Cliente.class, id);
		return ofy().load().key(k).get();
	}
}
