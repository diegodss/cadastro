package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Importancia  implements Serializable {

	@Id
	private Long id;
	
	private String nome;

	public Importancia() {
		
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Importancia outro = (Importancia) obj;
		boolean equal = (id != null && id.equals(outro.id))				
				|| (nome != null && nome.equals(outro.nome));
		return equal;
	}
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (nome == null ? 0 : nome.hashCode());
		
		return hash;
	}
	
}
