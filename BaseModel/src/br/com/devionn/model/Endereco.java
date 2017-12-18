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
public class Endereco extends BaseEntityImpl{
    
    @Column(length=100)
	private String bairro; 
	@Column(length = 100)
    private String rua;
    @Column(length = 50)
    private String numero;
    @Column(length = 200)
    private String complemento;	 
    private Integer tipo = 0;   
    private Boolean padrao;
    @Basic(optional = false)
    @Column(length=10)
	private String caixaPosta; 
    @Column(length=10)
	private String cep;
    private String codigo;
	@ManyToOne()
	@JoinColumn(name="idCidade", referencedColumnName="id")
	private Cidade cidade;
	@ManyToOne()
	@JoinColumn(name="idEstado", referencedColumnName="id")
	private Estado estado;
	@Column(name="idPessoa")
	private Long idPessoa;
	@ManyToOne
	@JoinColumn(name="idPessoa", referencedColumnName="id", insertable=false, updatable= false)
	private Pessoa pessoa;
	
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Boolean getPadrao() {
		return padrao;
	}
	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}
	public String getCaixaPosta() {
		return caixaPosta;
	}
	public void setCaixaPosta(String caixaPosta) {
		this.caixaPosta = caixaPosta;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
    
}
