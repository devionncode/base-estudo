package br.com.devionn.result;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ColunaResult implements Serializable {
	@Id
	private Long id;
	private String titulo;
	private String propriedade;
	private String variavel;
	private String tipo;
	private Integer posicao = 0; 
	private Double largura;
	private Boolean filtroPadrao = false; 
	private Boolean sortable = false;
	private String sortOrder = "ASC";
	private String alinhamento = "CENTER";
	private Boolean visivel = true;
	private String mascara;
	private Long idFiltro;
	
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
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getAlinhamento() {
		return alinhamento;
	}
	public void setAlinhamento(String alinhamento) {
		this.alinhamento = alinhamento;
	}
	public Boolean getVisivel() {
		return visivel;
	}
	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}
	
	public String getMascara() {
		return mascara;
	}
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}
	public Long getIdFiltro() {
		return idFiltro;
	}
	public void setIdFiltro(Long idFiltro) {
		this.idFiltro = idFiltro;
	}
	
	
}
