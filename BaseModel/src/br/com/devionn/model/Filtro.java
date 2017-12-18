package br.com.devionn.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity 
@Table(schema="public")
public class Filtro extends BaseEntityImpl {
	
	@Basic(optional = false)
	@Column(length = 5000)
	private String descricao = "";
	@Column(length = 100)
	private String nome;	 
	@Basic(optional = false)
	@Column(length = 50000)
	private String sql; 
	private Integer pageSize = 0;
	@Basic(optional = false)
	private Boolean utilizaPaginacao = true;
	private Integer banco = 0;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTela", referencedColumnName="id")
	private Tela tela;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUsuario", referencedColumnName="id")
	private Usuario usuario; 
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true,  targetEntity=Coluna.class)
	@JoinColumn(name="idFiltro", referencedColumnName="id")
	@OrderBy("posicao")
	private List<Coluna> colunas;
	private Boolean atualiza;
	private Integer intervaloAtualizacao;
	private String rowClass;
	
	public String getRowClass() {
		return rowClass;
	}
	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	public Tela getTela() {
		return tela;
	}
	public void setTela(Tela tela) {
		this.tela = tela;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Coluna> getColunas() {
		return colunas;
	}
	
	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}
	/**
	 * @return the atualiza
	 */
	public Boolean getAtualiza() {
		return atualiza;
	}
	/**
	 * @param atualiza the atualiza to set
	 */
	public void setAtualiza(Boolean atualiza) {
		this.atualiza = atualiza;
	}
	/**
	 * @return the intervaloAtualizacao
	 */
	public Integer getIntervaloAtualizacao() {
		return intervaloAtualizacao;
	}
	/**
	 * @param intervaloAtualizacao the intervaloAtualizacao to set
	 */
	public void setIntervaloAtualizacao(Integer intervaloAtualizacao) {
		this.intervaloAtualizacao = intervaloAtualizacao;
	} 
}
