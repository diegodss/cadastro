package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;


@Entity
public class Cadastro  implements Serializable {

	@Id
	private Long id;
	
	private String URL;
	private String imagem;
	private String importancia;
	private String senha;
	private String servico;
	private String usuario;
	private String test;

/*    private Cliente cliente;  
    teste de sync
    public Cliente getCliente() {  
        return cliente;  
    }  
  
    public void setCliente(Cliente cliente) {  
        this.cliente = cliente;  
    }  
  */
	
	@Index private Long clienteId;  
    
    public Long getClienteId() {  
        return clienteId;  
    }  
  
    public void setClienteId(Long clienteId) {  
        this.clienteId = clienteId;  
    }  

    // @Load public List<Ref<Person>> owners;
    //@Load Ref<Cliente> cliente;    

    //public Cliente getCliente() { return cliente.get(); }
    
    //public void setCliente(Cliente value) { cliente = Ref.create(value); }

	public Cadastro() {
		
	}
	
	public Long getId(){
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String URL){
		this.URL = URL;
	}
	public String getImagem(){
		return imagem;
	}
	public void setImagem(String imagem){
		this.imagem = imagem;
	}
	public String getImportancia(){
		return importancia;
	}
	public void setImportancia(String importancia){
		this.importancia = importancia;
	}
	public String getSenha(){
		return senha;
	}
	public void setSenha(String senha){
		this.senha = senha;
	}
	public String getServico(){
		return servico;
	}
	public void setServico(String servico){
		this.servico = servico;
	}
	public String getUsuario(){
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Cadastro outro = (Cadastro) obj;
		boolean equal = (id != null && id.equals(outro.id))
				|| (servico != null && servico.equals(outro.servico))
				|| (URL != null && URL.equals(outro.URL));
		return equal;
	}
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (servico == null ? 0 : servico.hashCode());
		hash = (31 * hash) + (URL == null ? 0 : URL.hashCode());
		
		return hash;
	}
	
}
