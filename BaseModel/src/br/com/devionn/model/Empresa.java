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
public class Empresa extends BaseEntityImpl {
	 
	@Column(length=150)
	private String fantasia;
	@Column(length=150)
	private String razaoSocial;
	@Column(length=15)
	private String cnpj;
	@Column(length=15)
	private Boolean ativo = true;
	@Column(length=15)
	private String telefone;
	@Column(length=15)
	private String fax;
	@Column(length=15)
	private String caixaPostal;
	@Column(length=100)
	private String bairro;
	@Column(length=200)
	private String complemento;
	@Column(length=50)
	private String numero;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idEstado", referencedColumnName="id")
    private Estado estado;
	@Column(length=100)
	private String rua;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCidade", referencedColumnName="id")
    private Cidade cidade; 
	@Column(length=10)
	private String cep;
	@Column(length=200)
	private String email; 
	@Column()
	private Long idTenant;
	
	public Long getIdTenant() {
		return idTenant;
	}
	
	public void setIdTenant(Long idTenant) {
		this.idTenant = idTenant;
	}
	
 	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCaixaPostal() {
		return caixaPostal;
	}
	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	 
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}  
	
}
