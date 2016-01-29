package br.com.yaw.spgae.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.yaw.spgae.model.Importancia;

public class ImportanciaDataSource implements Serializable, DataSource<Importancia> {
	
	private static Logger log = Logger.getLogger(ImportanciaDataSource.class);
	private Map<Long, Importancia> data = new LinkedHashMap<Long, Importancia>();
	
	@Override
	public void add(Importancia c) {
		if (c != null) {
			this.data.put(c.getId(), c);
		}
		updateSession();			
	}
	
	@Override 
	public void update(Importancia c) {
		add(c);
	}
	
	@Override
	public void remove(Importancia c) {
		if (c != null) {
			this.data.remove(c.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Importancia> collection) {
		log.debug("Sincronizando Importancia");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Importancia c: collection) {
			if (c != null) {
				this.data.put(c.getId(), c);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Importancia> getAll() {
		return new ArrayList<Importancia>(data.values());
	}
	
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	private void updateSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("forceGaeSessionSerialization", System.currentTimeMillis());		
	}
	
}
