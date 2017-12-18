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
public class VariavelFiltro extends BaseEntityImpl {
	
	
	@Basic(optional = false)
	@Column(length = 200)
	private String conteudo;
	@Basic(optional = false)
	@Column(length = 20)
	private String nome;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFiltro", referencedColumnName="id")
	private Filtro filtro;
	
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Filtro getFiltro() {
		return filtro;
	}
	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	
	
	
	

}
