package br.com.devionn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class Estado extends BaseEntityImpl {

	@Column(length=100)
	private String nome;
	@Column(length=2)
	private String uf;
	@ManyToOne
	@JoinColumn(name = "idPais", referencedColumnName="id")
	private Pais pais;
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
