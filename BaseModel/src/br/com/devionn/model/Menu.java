/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;



@Entity
@Table(schema="public")
public class Menu extends BaseEntityImpl {
    
     
	private String nome;
    @JoinColumn(name = "idmodulo",referencedColumnName = "id")
    @ManyToOne(fetch=FetchType.EAGER)
    private Modulo modulo;	
    private Integer posicao = 0;
    private Boolean ativo = true;
    private Boolean expandido = false;
    private String icone;
    private String descricao;  
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
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
	public Boolean getExpandido() {
		return expandido;
	}
	public void setExpandido(Boolean expandido) {
		this.expandido = expandido;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
    
    
}
