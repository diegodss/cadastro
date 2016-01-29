package br.com.yaw.spgae.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import br.com.yaw.spgae.dao.CadastroDAO;
import br.com.yaw.spgae.model.Cadastro;
import br.com.yaw.spgae.dao.ClienteDAO;
import br.com.yaw.spgae.dao.ImportanciaDAO;
import br.com.yaw.spgae.model.Cliente;
import br.com.yaw.spgae.model.Importancia;

@RequestMapping(value="/cadastro")
@Controller
public class CadastroController {

	private static Logger log = Logger.getLogger(CadastroController.class);
	@Autowired
	private CadastroDAO dao;
	
	@Autowired
	private ClienteDAO daocliente ;
	
	@Autowired
	private ImportanciaDAO daoimportancia;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar (Model uiModel) {
		
		List<Cadastro> cadastro = getDataSource().getAll();
		
		 for (Cadastro c : cadastro) {
			 String imagem = c.getImagem();
			 if (imagem.equals("")) {
				c.setImagem("/resources/img/icon-interrogacao.png");
			 }
		 }
		
		uiModel.addAttribute("cadastros",cadastro );
		return "cadastroLista";
	}
	
	@RequestMapping (params = "form", method = RequestMethod.GET)
	public String criarForm (Model uiModel) {
		
	    
	    List<Cliente> clientes = daocliente.getAll();
	    uiModel.addAttribute("clientesList", clientes);
	    
	    List<Importancia> importancias = daoimportancia.getAll();
	    uiModel.addAttribute("importanciaList", importancias);
	    
		uiModel.addAttribute("cadastro", new Cadastro());
		
		
		
		uiModel.addAttribute("active", "cadastroIncluir");
		log.debug("Pronto para incluir cadastro");
		return "cadastroIncluir";
	}
	@RequestMapping(method = RequestMethod.POST) 
	public String criar(@Valid Cadastro cadastro, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			log.debug("entrei para gravar" + bindingResult.toString());
			uiModel.addAttribute("cadastro", cadastro);
			uiModel.addAttribute("active", "cadastroIncluir");
			return "cadastroIncluir";
		}
		dao.save(cadastro);
		getDataSource().add(cadastro);
		log.debug("Cadastro gravado: " + cadastro.getId());
		return "redirect:/cadastro";
	}
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model uiModel) {
		Cadastro c = dao.findById(id);
		
		List<Cliente> clientes = daocliente.getAll();
		uiModel.addAttribute("clientesList", clientes);
		
		List<Importancia> importancias = daoimportancia.getAll();
		uiModel.addAttribute("importanciaList", importancias);
		
		if (c != null) {
			uiModel.addAttribute("cadastro", c);
			log.debug("Pronto para editar cadastro");			
		}
		return "cadastroEditar";
	}
	@RequestMapping(method = RequestMethod.PUT) 
	public String editar (@Valid Cadastro cadastro, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("cadastro", cadastro);
			return "cadastroEditar";
		}
		
		dao.save(cadastro);
		getDataSource().update(cadastro);
		log.debug("cadastro atualizado:" + cadastro.getId());
		return "redirect:/cadastro";
	}
	@RequestMapping(value ="/{id}", method=RequestMethod.DELETE)
	public String remover(@PathVariable("id") Long id, Model uiModel) {
		Cadastro c = dao.findById(id);
		if (c != null) {
			dao.remove(c);
			getDataSource().remove(c);
			log.debug("Cadastro removido: " + c.getId());
		}
		return "redirect:/cadastro";
	}
	@RequestMapping (value = "synch", method = RequestMethod.GET)
	public String atualizar() {
		getDataSource().synch(dao.getAll());
		return "redirect:/cadastro";
	}
	
	public CadastroDataSource getDataSource() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		CadastroDataSource ds = (CadastroDataSource) session.getAttribute("dsCadastro");
		if (ds == null) {
			ds = new CadastroDataSource();
			ds.synch(dao.getAll());
			session.setAttribute("dsCadastro", ds);
		}
		return ds;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Double.class, 
				new CustomNumberEditor(Double.class, NumberFormat.getInstance(new Locale("pt", "BR")), true));
	}
	
}