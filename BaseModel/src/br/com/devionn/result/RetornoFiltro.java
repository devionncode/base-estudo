package br.com.devionn.result;

import java.util.List;
import java.util.Map;

public class RetornoFiltro {

	private List<Map<String,Object>> colunas;
	private String mensagem;
	private Boolean sucesso;
	private List dados;
	
	public List<Map<String, Object>> getColunas() {
		return colunas;
	}
	public void setColunas(List<Map<String, Object>> colunas) {
		this.colunas = colunas;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Boolean getSucesso() {
		return sucesso;
	}
	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}
	public List getDados() {
		return dados;
	}
	public void setDados(List dados) {
		this.dados = dados;
	}
	
	
}
