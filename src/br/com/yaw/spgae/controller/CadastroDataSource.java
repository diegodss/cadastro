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

import br.com.yaw.spgae.model.Cadastro;

public class CadastroDataSource implements Serializable, DataSource<Cadastro> {
	
	private static Logger log = Logger.getLogger(CadastroDataSource.class);
	private Map<Long, Cadastro> data = new LinkedHashMap<Long, Cadastro>();
	
	@Override
	public void add(Cadastro c) {
		if (c != null) {
			this.data.put(c.getId(), c);
		}
		updateSession();			
	}
	
	@Override 
	public void update(Cadastro c) {
		add(c);
	}
	
	@Override
	public void remove(Cadastro c) {
		if (c != null) {
			this.data.remove(c.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Cadastro> collection) {
		log.debug("Sincronizando cadastro");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Cadastro c: collection) {
			if (c != null) {
				this.data.put(c.getId(), c);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Cadastro> getAll() {
		return new ArrayList<Cadastro>(data.values());
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
