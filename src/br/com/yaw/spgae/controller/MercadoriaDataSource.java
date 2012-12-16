package br.com.yaw.spgae.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.yaw.spgae.model.Mercadoria;

public class MercadoriaDataSource implements Serializable {
	
	private static Logger log = Logger.getLogger(MercadoriaDataSource.class);
	
	private Map<Long, Mercadoria> data = new LinkedHashMap<Long, Mercadoria>();
	
	public void add(Mercadoria m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
	}
	
	public void update(Mercadoria m) {
		add(m);
	}
	
	public void remove(Mercadoria m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
	}
	
	public void synch(Collection<Mercadoria> collection) {
		log.debug("Sincronizando datastore de mercadorias...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Mercadoria m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
	}
	
	public List<Mercadoria> getAll() {
		return new ArrayList<Mercadoria>(data.values());
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}

}
