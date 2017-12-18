package br.com.devionn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class GrupoUsuario extends BaseEntityImpl {

	@Column(length=30)
	private String nome;
	@Column(length=20)
	private String cor;
	private Boolean ativo = true;
	@Column(length=100)
	private String descricao;
	@Column(name="idempresa")
	private Long idEmpresa;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idusuarioalteracao", referencedColumnName="id")
	private Usuario usuarioCadastro;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idusuariocadastro", referencedColumnName="id")
	private Usuario usuarioAlteracao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	 
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
}
