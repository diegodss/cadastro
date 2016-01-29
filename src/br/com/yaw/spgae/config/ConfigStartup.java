package br.com.yaw.spgae.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.yaw.spgae.model.Cadastro;
import br.com.yaw.spgae.model.Cliente;
import br.com.yaw.spgae.model.Importancia;
import com.googlecode.objectify.ObjectifyService;

/**
 * Componente necessário para registrar no Objectify quais são as entidades que ele deve gerenciar.
 * 
 * <p>Código executado durante a inicialização do aplicativo web.</p> 
 * 
 * @author YaW Tecnologia
 */
public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Cadastro.class);
		ObjectifyService.register(Cliente.class);
		ObjectifyService.register(Importancia.class);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
	
}
