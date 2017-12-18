package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.TenantBeanRemote;
import br.com.devionn.model.Tenant;

@Stateless
@Path("tenant")
public class TenantWs extends AbstractWs<Tenant>{
	
	@EJB
	private TenantBeanRemote tenantBean;
	
	@Override
	public AbstractBean<Tenant> getDAS() {
		return tenantBean;

    }

}
