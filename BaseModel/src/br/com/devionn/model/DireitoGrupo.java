package br.com.devionn.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;



@Entity
@Table(schema="public", indexes={ 
		@Index(columnList="idgrupousuario,status,idtela,deletado", name="direitoacessoportela_idx")
})
public class DireitoGrupo extends BaseEntityImpl {

	private Boolean status = false;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idacao", referencedColumnName="id")
	private Acao acao;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idtela", referencedColumnName="id")
	private Tela tela;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idusuariocadastro", referencedColumnName="id")
	private Usuario usuarioAlteracao;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idgrupousuario", referencedColumnName="id")
	private GrupoUsuario  grupoUsuario;
	
	
	
	
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Tela getTela() {
		return tela;
	}
	public void setTela(Tela tela) {
		this.tela = tela;
	}
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}
	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	 
}
