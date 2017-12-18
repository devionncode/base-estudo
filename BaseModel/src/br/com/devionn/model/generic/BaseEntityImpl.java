package br.com.devionn.model.generic;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass 
public abstract class BaseEntityImpl implements BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    protected Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition=" timestamp not null default current_timestamp ")
    protected Date dataCadastro;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition=" timestamp not null default current_timestamp ")
    protected Date dataAlteracao;
    @Version
    @Column(columnDefinition=" bigint not null default 0 ")
    protected Long versao; 
    @Column(columnDefinition=" Boolean not null default false ")
    protected Boolean deletado = false;

    public Boolean getDeletado() {
		return deletado;
	}
    
    public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}
    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return getVersao() == null;
    }
    
    
}
