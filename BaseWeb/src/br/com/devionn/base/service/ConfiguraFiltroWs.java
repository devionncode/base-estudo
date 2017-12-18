package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.ColunaBeanLocal;
import br.com.devionn.model.Coluna;

@Stateless
@Path("configuraFiltro")
public class ConfiguraFiltroWs extends AbstractWs<Coluna>{
	
	@EJB
	private ColunaBeanLocal configuraFiltroBean;
	
	@Override
	public AbstractBean<Coluna> getDAS() {
		return configuraFiltroBean;
	}
	
	
	

}
