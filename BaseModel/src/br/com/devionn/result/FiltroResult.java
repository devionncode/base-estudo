package br.com.devionn.result;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FiltroResult implements Serializable {

	@Id
	private Long id;
	private String nome;	 
	private String sql; 
	private Integer pageSize = 0;
	private Boolean utilizaPaginacao = true;
	private Long idTela;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Boolean getUtilizaPaginacao() {
		return utilizaPaginacao;
	}
	public void setUtilizaPaginacao(Boolean utilizaPaginacao) {
		this.utilizaPaginacao = utilizaPaginacao;
	}
	public Long getIdTela() {
		return idTela;
	}
	public void setIdTela(Long idTela) {
		this.idTela = idTela;
	}
	
	
}
