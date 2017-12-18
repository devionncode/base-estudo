package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.ModuloBeanLocal;
import br.com.devionn.model.Modulo;

@Stateless
@Path("modulo")
public class ModuloWs extends AbstractWs<Modulo> {
	
	@EJB
	private ModuloBeanLocal moduloBean;
	
	@Override
	public AbstractBean<Modulo> getDAS() {
		return moduloBean;
	}

}
