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

import br.com.yaw.spgae.model.Cliente;

public class ClienteDataSource implements Serializable, DataSource<Cliente> {
	
	private static Logger log = Logger.getLogger(ClienteDataSource.class);
	private Map<Long, Cliente> data = new LinkedHashMap<Long, Cliente>();
	
	@Override
	public void add(Cliente c) {
		if (c != null) {
			this.data.put(c.getId(), c);
		}
		updateSession();			
	}
	
	@Override 
	public void update(Cliente c) {
		add(c);
	}
	
	@Override
	public void remove(Cliente c) {
		if (c != null) {
			this.data.remove(c.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Cliente> collection) {
		log.debug("Sincronizando cliente");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Cliente c: collection) {
			if (c != null) {
				this.data.put(c.getId(), c);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Cliente> getAll() {
		return new ArrayList<Cliente>(data.values());
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
