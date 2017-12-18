package br.com.devionn.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filter {
	
	private Long page;
	private Long start;
	private Long limit;
	private Long idFiltro;
	private List<Clausula> clausulas;
	private List<Variavel> variaveis = new ArrayList<Variavel>();
	private Object data;
	private Boolean sucesso = false;
	private String token;
	private Date ultimaBusca;
	private Integer intervalo;
	
	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getSucesso() {
		return sucesso;
	}
	
	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}
	
	public List<Variavel> getVariaveis() {
		return variaveis;
	}
	
	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getLimit() {
		return limit;
	}
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	public List<Clausula> getClausulas() {
		return clausulas;
	}
	public void setClausulas(List<Clausula> clausulas) {
		this.clausulas = clausulas;
	}

	public Long getIdFiltro() {
		return idFiltro;
	}
	
	public void setIdFiltro(Long idFiltro) {
		this.idFiltro = idFiltro;
	}

	/**
	 * @return the ultimaBusca
	 */
	public Date getUltimaBusca() {
		return ultimaBusca;
	}

	/**
	 * @param ultimaBusca the ultimaBusca to set
	 */
	public void setUltimaBusca(Date ultimaBusca) {
		this.ultimaBusca = ultimaBusca;
	}

}
