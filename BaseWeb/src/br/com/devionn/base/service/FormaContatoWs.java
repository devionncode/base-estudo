package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.FormaContatoBeanLocal;
import br.com.devionn.model.FormaContato;

@Stateless
@Path("formaContato")
public class FormaContatoWs extends AbstractWs<FormaContato> {
	
	@EJB
	private FormaContatoBeanLocal formaContatoBean;
	
	@Override
	public AbstractBean<FormaContato> getDAS() {
		return formaContatoBean;
	}
	

}
