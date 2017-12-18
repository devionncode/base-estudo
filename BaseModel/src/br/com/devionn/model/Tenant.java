package br.com.devionn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity
@Table(schema="public", indexes={ 
		@Index(columnList="schemaName,deletado", name="tenant_schemaname_idx")
})
public class Tenant extends BaseEntityImpl {

    @Column
    private String unitName;

    @Column(nullable = false, updatable = false)
    private String schemaName;

    @Column(nullable = false, updatable = true)
    private String password;
     

    public Tenant() {
    }

    
    public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
 

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}


	public String getSchemaName() {
        return schemaName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
