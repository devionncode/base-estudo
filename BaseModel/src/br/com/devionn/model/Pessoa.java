
package br.com.devionn.model;
 
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;
 

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(schema="public")
public class Pessoa extends BaseEntityImpl {
 	 
    @Column(length = 150)
    private String razaoSocial;
    @Column(length = 20)
    private String cnpj;
    @Column(length = 150)
    private String fantasia;
    @Basic(optional = false)
    private Boolean juridica = true;
    @Basic(optional = false)
    @Column(length  = 1)
    private String sexo = "M";
    @Basic(optional = false)
    @Column(length  = 50)
    private String telefone = "";
    @Basic(optional = true)
    @Column(length  = 50)
    private String celular = "" ;
    @Basic(optional = true)
    @Column(length  = 200)
    private String email ;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval=false, mappedBy="pessoa")
    private List<Endereco> enderecos; 
    
    public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

    public String getFantasia() {
		return fantasia;
	}
    
    public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
    
	public Boolean getJuridica() {
		return juridica;
	}

	public void setJuridica(Boolean juridica) {
		this.juridica = juridica;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

     
}
