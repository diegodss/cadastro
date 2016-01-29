package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.googlecode.objectify.Key;
import br.com.yaw.spgae.model.Importancia;

@Repository
public class ImportanciaDAOObjectify implements Serializable, ImportanciaDAO {

	@Override
	public Importancia save(Importancia Importancia) {
		ofy().save().entity(Importancia).now();
		return Importancia;
	}
	
	@Override
	public List<Importancia> getAll(){
		return ofy().load().type(Importancia.class).list();		
	}
	
	@Override
	public Boolean remove(Importancia Importancia) {
		ofy().delete().entity(Importancia).now();
		return true;
	}
	@Override
	public Importancia findById(Long id) {
		Key<Importancia> k = Key.create(Importancia.class, id);
		return ofy().load().key(k).get();
	}
}
