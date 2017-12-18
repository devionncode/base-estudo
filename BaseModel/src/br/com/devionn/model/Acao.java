package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;


@Entity
@Table(schema="public")
public class Acao extends BaseEntityImpl {

	@Column(length=200)
	@Basic(optional = false)
	private String nome;
	@Column(length=100)
	private String action;
	@Column(length=500)
	@Basic(optional = false)
	private String descricao;
	@Column(length=500)
	@Basic(optional = false)
	private String icone; 
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone; 
	}  
	
}
