package br.com.devionn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public")
public class ContaEmail extends BaseEntityImpl implements IContaEmail{

	@Column(length=10)
	private String protocolo;
	private Boolean tlsAtivo;
	private Boolean autenticar;
	@Column(length=200)
	private String email;
	@Column(length=50)
	private String senha;
	private Integer porta;
	@Column(length=200)
	private String host;
	@ManyToOne
	@JoinColumn(name="idEmpresa", referencedColumnName="id", updatable=false, insertable=false)
	private Empresa empresa;
	private Long idEmpresa;
	
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public Boolean getTlsAtivo() {
		return tlsAtivo;
	}
	public void setTlsAtivo(Boolean tlsEnable) {
		this.tlsAtivo = tlsEnable;
	}
	public Boolean getAutenticar() {
		return autenticar;
	}
	public void setAutenticar(Boolean autentica) {
		this.autenticar = autentica;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String login) {
		this.email = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		if (empresa != null){
			this.idEmpresa = empresa.getId();
		}
	}
	
}
