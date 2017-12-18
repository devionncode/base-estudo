package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.IdiomaBeanLocal;
import br.com.devionn.model.Idioma;

@Stateless
@Path("idioma")
public class IdiomaWs extends AbstractWs<Idioma> {
	
	@EJB
	private IdiomaBeanLocal idiomaBean;
	
	@Override
	public AbstractBean<Idioma> getDAS() {
		return idiomaBean;
	}
	

}
