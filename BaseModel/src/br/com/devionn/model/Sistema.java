package br.com.devionn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class Sistema extends BaseEntityImpl{
 
	private Long build;
	@Column(length=100)
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataBuild;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private Usuario usuario;
	@Column(columnDefinition=" text  ")
	private String logo;
	
	
	public Date getDataBuild() {
		return dataBuild;
	}
	
	public void setDataBuild(Date dataBuild) {
		this.dataBuild = dataBuild;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuarioAlteracao) {
		this.usuario= usuarioAlteracao;
	}
	
	public Long getBuild() {
		return build;
	}
	
	public void setBuild(Long build) {
		this.build = build;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}	
}
