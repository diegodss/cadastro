package br.com.yaw.spgae.controller;
/*
 * Para criar uma nova pagina :
 * 
 * 1. Criar a pagina jsp
 * 2. colocar a rota em views.xml
 * 3. Neste controle criar un get para a pagina criada
 * */
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.yaw.spgae.config.ValidaUsuario;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@RequestMapping(value="/")
@Controller
public class MainController {
	@Autowired @Qualifier("sobreApp") 
	private ArrayList<?> sobre;
	
	@RequestMapping(value = "sobre", method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute("sobre",sobre);
		uiModel.addAttribute("active", "sobre");
		return "sobre";
	}
	@RequestMapping(value = "loginInvalido", method = RequestMethod.GET)
	public String loginInvalido(Model uiModel) {
		
		
		return "loginInvalido";
	}
	@RequestMapping(method = RequestMethod.GET)
	public String validaAcessoRoot(Model uiModel) {
		
		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("redirect:/cadastro");
		return ret;
		
	}
	
}
