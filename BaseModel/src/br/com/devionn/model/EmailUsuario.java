package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class EmailUsuario extends BaseEntityImpl implements IContaEmail{

	@Basic(optional = false)
	@Column(length=50)
	private String host;
	private Integer porta;
	@Basic(optional = false)
	@Column(length=50)
	private String senha;
	@Basic(optional = false)
	@Column(length=50)
	private String email;
	@Basic(optional = false)
	private Boolean autenticar;
	private Boolean tlsAtivo;
	@Basic(optional = false)
	@Column(length=10)
	private String protocolo;
	private String assinatura;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUsuario", referencedColumnName="id")
    private  Usuario usuario;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getAutenticar() {
		return autenticar;
	}
	public void setAutenticar(Boolean autenticar) {
		this.autenticar = autenticar;
	}
	public Boolean getTlsAtivo() {
		return tlsAtivo;
	}
	public void setTlsAtivo(Boolean tlsAtivo) {
		this.tlsAtivo = tlsAtivo;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getAssinatura() {
		return assinatura;
	}
	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
