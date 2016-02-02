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
import br.com.yaw.spgae.dao.ImportanciaDAO;
import br.com.yaw.spgae.model.Importancia;

@RequestMapping(value="/importancia")
@Controller
public class ImportanciaController {

	private static Logger log = Logger.getLogger(ImportanciaController.class);
	@Autowired
	private ImportanciaDAO dao;
		
	@RequestMapping(method = RequestMethod.GET)
	public String listar (Model uiModel) {
		uiModel.addAttribute("importancias", getDataSource().getAll());

		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("importanciaLista");
		return ret;
				
	}
	
	@RequestMapping (params = "form", method = RequestMethod.GET)
	public String criarForm (Model uiModel) {
		uiModel.addAttribute("importancia", new Importancia());
		uiModel.addAttribute("active", "importanciaIncluir");
		log.debug("Pronto para incluir importancia");

		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("importanciaIncluir");
		return ret;
			
	}
	@RequestMapping(method = RequestMethod.POST) 
	public String criar(@Valid Importancia importancia, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("importancia", importancia);
			uiModel.addAttribute("active", "importanciaIncluir");
			return "importanciaIncluir";
		}
		dao.save(importancia);
		getDataSource().add(importancia);
		log.debug("Importancia gravado: " + importancia.getId());
		return "redirect:/importancia";
	}
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model uiModel) {
		Importancia c = dao.findById(id);
		if (c != null) {
			uiModel.addAttribute("importancia", c);
			log.debug("Pronto para editar importancia");			
		}
		
		ValidaUsuario vu = new ValidaUsuario();		 		 
		String ret = vu.validaAcesso("importanciaEditar");
		return ret;
	}
	@RequestMapping(method = RequestMethod.PUT) 
	public String editar (@Valid Importancia importancia, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("importancia", importancia);
			return "importanciaEditar";
		}
		
		dao.save(importancia);
		getDataSource().update(importancia);
		log.debug("importancia atualizado:" + importancia.getId());
		return "redirect:/importancia";
	}
	@RequestMapping(value ="/{id}", method=RequestMethod.DELETE)
	public String remover(@PathVariable("id") Long id, Model uiModel) {
		Importancia c = dao.findById(id);
		if (c != null) {
			dao.remove(c);
			getDataSource().remove(c);
			log.debug("Importancia removido: " + c.getId());
		}
		return "redirect:/importancia";
	}
	@RequestMapping (value = "synch", method = RequestMethod.GET)
	public String atualizar() {
		getDataSource().synch(dao.getAll());
		return "redirect:/importancia";
	}
	
	public ImportanciaDataSource getDataSource() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		ImportanciaDataSource ds = (ImportanciaDataSource) session.getAttribute("dsImportancia");
		if (ds == null) {
			ds = new ImportanciaDataSource();
			ds.synch(dao.getAll());
			session.setAttribute("dsImportancia", ds);
		}
		return ds;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Double.class, 
				new CustomNumberEditor(Double.class, NumberFormat.getInstance(new Locale("pt", "BR")), true));
	}
	
}