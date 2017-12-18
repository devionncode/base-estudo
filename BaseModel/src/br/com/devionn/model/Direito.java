package br.com.devionn.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Direito implements Serializable {

	@Id
	private Long id;
	private Long idAcao;
	private Long idTela;
	private Boolean status;
	private String descricao;
	private String icone;
	private String nome;
	private String action;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}
	public Long getIdTela() {
		return idTela;
	}
	public void setIdTela(Long idTela) {
		this.idTela = idTela;
	}
	 
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	
	
	
	
}
