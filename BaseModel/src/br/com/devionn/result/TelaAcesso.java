package br.com.devionn.result;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TelaAcesso implements Serializable {

	@Id
	private Long id;
	private String nome;
	private String descricao;
    private Integer posicao = 0;
    private String url;
    private Boolean ativo = true;
    private Integer grupo = 0;
    private String icone; 
	private Long idMenu;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public Long getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}
	
	
	
}
