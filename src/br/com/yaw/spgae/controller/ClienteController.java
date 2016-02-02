package br.com.yaw.spgae.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.yaw.spgae.config.ValidaUsuario;
import br.com.yaw.spgae.dao.ClienteDAO;
import br.com.yaw.spgae.model.Cliente;

@RequestMapping(value="/cliente")
@Controller
public class ClienteController {

	private static Logger log = Logger.getLogger(ClienteController.class);
	@Autowired
	private ClienteDAO dao;
		
	@RequestMapping(method = RequestMethod.GET)
	public String listar (Model uiModel) {
		uiModel.addAttribute("clientes", getDataSource().getAll());
		
		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("clienteLista");
		return ret;
			
	}
	
	@RequestMapping (params = "form", method = RequestMethod.GET)
	public String criarForm (Model uiModel) {
		uiModel.addAttribute("cliente", new Cliente());
		uiModel.addAttribute("active", "clienteIncluir");
		log.debug("Pronto para incluir cliente");
		
		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("clienteIncluir");
		return ret;
				
	}
	@RequestMapping(method = RequestMethod.POST) 
	public String criar(@Valid Cliente cliente, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("cliente", cliente);
			uiModel.addAttribute("active", "clienteIncluir");
			return "clienteIncluir";
		}
		dao.save(cliente);
		getDataSource().add(cliente);
		log.debug("Cliente gravado: " + cliente.getId());
		return "redirect:/cliente";
	}
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model uiModel) {
		Cliente c = dao.findById(id);
		if (c != null) {
			uiModel.addAttribute("cliente", c);
			log.debug("Pronto para editar cliente");			
		}
		
		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("clienteEditar");
		return ret;
		
		
	}
	@RequestMapping(method = RequestMethod.PUT) 
	public String editar (@Valid Cliente cliente, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("cliente", cliente);
			return "clienteEditar";
		}
		
		dao.save(cliente);
		getDataSource().update(cliente);
		log.debug("cliente atualizado:" + cliente.getId());
		return "redirect:/cliente";
	}
	@RequestMapping(value ="/{id}", method=RequestMethod.DELETE)
	public String remover(@PathVariable("id") Long id, Model uiModel) {
		Cliente c = dao.findById(id);
		if (c != null) {
			dao.remove(c);
			getDataSource().remove(c);
			log.debug("Cliente removido: " + c.getId());
		}
		return "redirect:/cliente";
	}
	@RequestMapping (value = "synch", method = RequestMethod.GET)
	public String atualizar() {
		getDataSource().synch(dao.getAll());
		return "redirect:/cliente";
	}
	
	public ClienteDataSource getDataSource() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		ClienteDataSource ds = (ClienteDataSource) session.getAttribute("dsCliente");
		if (ds == null) {
			ds = new ClienteDataSource();
			ds.synch(dao.getAll());
			session.setAttribute("dsCliente", ds);
		}
		return ds;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Double.class, 
				new CustomNumberEditor(Double.class, NumberFormat.getInstance(new Locale("pt", "BR")), true));
	}
	
}