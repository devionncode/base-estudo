package br.com.devionn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;



@Entity
@Table(schema="public")
public class Modulo extends BaseEntityImpl{
    
    
	@Column(length=50)
    private String descricao;
    private Integer posicao = 0;
    private Boolean ativo = true;
    private Boolean principal = true;
//    @OneToMany(fetch=FetchType.LAZY,mappedBy="modulo",  orphanRemoval=true)
//    private List<Menu> menus;
    private Boolean mobile;
    
    
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
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getMobile() {
		return mobile;
	}
	public void setMobile(Boolean mobile) {
		this.mobile = mobile;
	}

	public Boolean getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
    
}
