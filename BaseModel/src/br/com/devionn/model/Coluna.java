package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public", indexes={ 
		@Index(columnList="idfiltro,deletado", name="coluna_idfiltro_idx")
})
public class Coluna extends BaseEntityImpl {

	@Basic(optional = false)
	@Column(length = 150)
	private String titulo;
	@Basic(optional = false)
	@Column(length = 50)
	private String propriedade;
	@Column(length = 800)
	private String variavel;
	@Basic(optional = false)
	@Column(length = 10)
	private String tipo;
	private Integer posicao = 0; 
	@Basic(optional = false)
	@Column(precision = 5, scale = 2)
	private Double largura;
	@Basic(optional = false)
	private Boolean filtroPadrao = false; 
	@Basic(optional = false)
	private Boolean sortable = false;
	@Basic(optional = false)
	@Column(length = 5)
	private String sortOrder = "ASC";
	@Basic(optional = true)
	@Column(length = 25)
	private String alinhamento = "CENTER";
	@Basic(optional = false)
	private Boolean visivel = true;
	@Column(name="idfiltro")
	private Long idFiltro;
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=Filtro.class)
	@JoinColumn(name = "idFiltro", referencedColumnName = "id", insertable=false, updatable=false)
	@Basic(optional=false)
	private Filtro filtro;
	@Basic(optional = true)
	@Column(length = 100)
	private String mascara;
	
	public Long getIdFiltro() {
		return idFiltro;
	}
	
	public void setIdFiltro(Long idFiltro) {
		this.idFiltro = idFiltro;
	}
	
	public String getAlinhamento() {
		return alinhamento;
	}
	
	public void setAlinhamento(String alinhamento) {
		this.alinhamento = alinhamento;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPropriedade() {
		return propriedade;
	}
	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}
	public String getVariavel() {
		return variavel;
	}
	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	public Double getLargura() {
		return largura;
	}
	public void setLargura(Double largura) {
		this.largura = largura;
	}
	public Boolean getFiltroPadrao() {
		return filtroPadrao;
	}
	public void setFiltroPadrao(Boolean filtroPadrao) {
		this.filtroPadrao = filtroPadrao;
	}
	 
	public Boolean getSortable() {
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sorOrder) {
		this.sortOrder = sorOrder;
	}
	
	public Boolean getVisivel() {
		return visivel;
	}
	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}
	 
	public Filtro getFiltro() {
		return filtro;
	}
	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

}
