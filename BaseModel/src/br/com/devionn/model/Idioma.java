package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class Idioma extends BaseEntityImpl{
	
	
	@Basic(optional = false)
	@Column(length = 100)
	private String nome;
	@Basic(optional = false)
	@Column(length = 50)
	private String coluna;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	
	
	
	
	
	
	
	
	
	
	

}
