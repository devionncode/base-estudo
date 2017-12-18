package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;


@Entity
@Table(schema="public")
public class Dispositivo extends BaseEntityImpl {

	@ManyToOne()
	@JoinColumn(name="idusuario", referencedColumnName="id", insertable=false, updatable=false)
	private Usuario usuario;
	private Long idUsuario;
	@Column(length=200)
	@Basic(optional = false)
	private String nome;
	@Column(length=500)
	@Basic(optional = false)
	private String plataforma;
	@Column(length=500)
	@Basic(optional = true)
	private String uuid; 
	private String versaoSistema;
	private String serial;
	@Column(length=2000)
	private String token;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		if (usuario != null){
			this.idUsuario = usuario.getId();
		}
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getVersaoSistema() {
		return versaoSistema;
	}
	public void setVersaoSistema(String versaoSistema) {
		this.versaoSistema = versaoSistema;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
		
}
