package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.VariavelFiltroBeanLocal;
import br.com.devionn.model.VariavelFiltro;

@Stateless
@Path("variavelFiltro")
public class VariavelFiltroWs extends AbstractWs<VariavelFiltro>{
	
	@EJB
	private VariavelFiltroBeanLocal variavelFiltroBean;
	
	@Override
	public AbstractBean<VariavelFiltro> getDAS() {
		return variavelFiltroBean;
	}

}
