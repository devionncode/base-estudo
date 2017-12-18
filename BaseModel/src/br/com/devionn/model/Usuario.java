package br.com.devionn.model;
 
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SqlResultSetMapping(name="UsuarioLogado", 
	classes={@ConstructorResult(targetClass=Usuario.class, columns={
			@ColumnResult(name="login",type=String.class),
			@ColumnResult(name="id",type=Long.class), 
			@ColumnResult(name="token",type=String.class),
			@ColumnResult(name="idEmpresa",type=Long.class),
			@ColumnResult(name="razaoSocial",type=String.class),			
			@ColumnResult(name="fantasia",type=String.class),			
			@ColumnResult(name="cnpj"),
			@ColumnResult(name="schemaName",type=String.class),			
			@ColumnResult(name="idGrupoUsuario",type=Long.class),
			@ColumnResult(name="cnpjEmpresa",type=String.class),
			@ColumnResult(name="versao",type=Long.class),
			@ColumnResult(name="idDispositivo",type=Long.class)
			
	})} 
 )
/**
entities=@EntityResult(entityClass=Usuario.class,
	fields={
			@FieldResult(name="id",column="id"),
			@FieldResult(name="razaoSocial",column="razaoSocial"),
			@FieldResult(name="fantasia",column="fantasia"),
			@FieldResult(name="token",column="token"),
			@FieldResult(name="idEmpresa",column="idEmpresa"),
			@FieldResult(name="schemaName",column="schemaName"),
			@FieldResult(name="login",column="login"),
			@FieldResult(name="cnpj",column="cnpj"),
 	})
 * @author anderson
 *
 */
@Entity
@Table(name="usuario",schema="public", 
	indexes={
		@Index(columnList="token", name="usuario_token_idx")
})
@PrimaryKeyJoinColumn(name="id")
public class Usuario extends Pessoa {
   

	@Basic(optional = false) 
    @Column(length = 100)
    private String login;
	@Basic(optional = false) 
    @Column(length = 100)
    private String senha;
    @Column(length = 500)
    private String observacao;
    private Boolean ativo = true; 
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataHoraCadastro;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataHoraAlteracao;
    @Column(name="ididioma", updatable=false, insertable=false)
    private Long idIdioma;
    @Column(length=2000)
    private String token;
    @JoinColumn(name = "ididioma",referencedColumnName = "id")
    @ManyToOne(fetch=FetchType.EAGER)
    private Idioma idioma;    
    @Column(name="idempresa")
    private Long idEmpresa;
    @JoinColumn(name = "idgrupoUsuario",referencedColumnName = "id")
    @ManyToOne(fetch=FetchType.EAGER)
    private  GrupoUsuario grupoUsuario;
    @Column(name="idGrupoUsuario", updatable=false, insertable=false)
    private Long idGrupoUsuario;
    private Long idUsuarioAlteracao;
    
    @Transient 
    private String schemaName;
    @Transient 
    private String cnpjEmpresa;
    @Transient
    private Long idDispositivo;
    
    public Usuario() {
	}
    
    public Usuario(String login,Long id, String token,Long idempresa, String razaoSocial, String fantasia, String cnpj, String schemaName, Long idGrupoUsuario,String cnpjEmpresa,Long versao,Long idDispositivo){
    	this.login = login;
    	this.id = id;
    	this.token = token;
    	this.setRazaoSocial(razaoSocial);
    	this.setFantasia(fantasia);
    	this.setCnpj(cnpj);
    	this.schemaName = schemaName;
    	this.idEmpresa = idempresa;
    	this.idGrupoUsuario = idGrupoUsuario;
    	this.cnpjEmpresa = cnpjEmpresa;
    	this.versao =versao;
    	this.idDispositivo = idDispositivo;
    }
    
    public Long getIdDispositivo() {
		return idDispositivo;
	}
    
    public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
    
    public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}
    
    public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
    
    public Long getIdGrupoUsuario() {
		return idGrupoUsuario;
	}
    
    public void setIdGrupoUsuario(Long idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}
    
    public String getSchemaName() {
		return schemaName;
	}
    
    public void setSchemaName(String schema) {
		this.schemaName = schema;
	}
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	public Date getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}
	public void setDataHoraAlteracao(Date dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
		if ( idioma != null){
			this.idIdioma = idioma.getId();
		}
	}
	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}
	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
		if (grupoUsuario != null){
			this.idGrupoUsuario = grupoUsuario.getId();
		}
	}
    
    
    
    public void setIdIdioma(Long idIdioma) {
		this.idIdioma = idIdioma;
	}
    
    public Long getIdIdioma() {
		return idIdioma;
	}
    
    public Long getIdEmpresa() {
		return idEmpresa;
	}
    
    public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
    
    public String getToken() {
		return token;
	}
    
    public void setToken(String token) {
		this.token = token;
	}
    
    public Long getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}
    
    public void setIdUsuarioAlteracao(Long idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
    
    
}
