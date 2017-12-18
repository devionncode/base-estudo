package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public", indexes={ 
		@Index(columnList="idestado,deletado", name="cidade_idestado_idx")
})
public class Cidade extends BaseEntityImpl {
   
	@Basic(optional = false)
	@Column(length=100)
	private String nome;
	private Integer ddd;
	@ManyToOne
	@JoinColumn(name="idestado", referencedColumnName="id")
	private Estado estado;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
