package br.com.devionn.das;

import javax.ejb.Remote;
import br.com.devionn.model.Tenant;

@Remote
public interface TenantBeanRemote extends AbstractBean<Tenant>{

	public void createSchema(Tenant tenant)throws Exception;
	
	public Tenant getTenantPorToken(String token)throws Exception;
	
	public Tenant getTenantPorSchemaName(String schemaName)throws Exception; 
}

