package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Cliente  implements Serializable {

	@Id
	private Long id;
	
	private String nome;
	private String rut;
	private String contato;

	public Cliente() {
		
	}
	
	public Long getId(){
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getnome() {
		return nome;
	}
	public void setnome(String nome){
		this.nome = nome;
	}
	public String getrut(){
		return rut;
	}
	public void setrut(String rut){
		this.rut = rut;
	}
	public String getContato(){
		return contato;
	}
	public void setContato(String contato){
		this.contato = contato;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Cliente outro = (Cliente) obj;
		boolean equal = (id != null && id.equals(outro.id))
				|| (contato != null && contato.equals(outro.contato))
				|| (nome != null && nome.equals(outro.nome));
		return equal;
	}
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (contato == null ? 0 : contato.hashCode());
		hash = (31 * hash) + (nome == null ? 0 : nome.hashCode());
		
		return hash;
	}
	
}
