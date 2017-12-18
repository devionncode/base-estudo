package br.com.devionn.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class TelaAcao extends BaseEntityImpl {

	@JoinColumn(name="idtela", referencedColumnName="id", insertable = false, updatable = false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Tela tela;
	@JoinColumn(name="idacao", referencedColumnName="id", insertable = false, updatable = false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Acao acao;
	private Integer posicao;
	private Long idAcao;
	private Long idTela;
	
	public Long getIdTela() {
		return idTela;
	}
	public void setIdTela(Long idTela) {
		this.idTela = idTela;
	}
	public Long getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}
	public Tela getTela() {
		return tela;
	}
	public void setTela(Tela tela) {
		if (tela != null) {
			this.idTela = tela.getId();
		}
		this.tela = tela;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		if (acao != null) {
			this.idAcao = acao.getId();
		}
		this.acao = acao;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	} 
	
}
