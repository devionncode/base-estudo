package br.com.devionn.model.generic;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author anderson
 */
public interface BaseEntity extends Serializable {
    
    public Long getId();
    
    public void setId(Long id);
    
    public boolean isNew();
    
    public abstract Boolean getDeletado();
    
    public abstract void setDeletado(Boolean deletado);
    
    public abstract Date getDataCadastro();

    public abstract void setDataCadastro(Date dataCadastro);

    public abstract Date getDataAlteracao();

    public abstract void setDataAlteracao(Date dataAlteracao); 

    public abstract Long getVersao();

    public abstract void setVersao(Long versao);
    
}
