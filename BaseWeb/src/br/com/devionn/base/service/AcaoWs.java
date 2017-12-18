package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.AcaoBeanLocal;
import br.com.devionn.model.Acao;

@Stateless
@Path("acao")
public class AcaoWs extends AbstractWs <Acao> {
	
	@EJB
	private AcaoBeanLocal acaoBean;
	
	@Override
	public AbstractBean<Acao> getDAS() {
		return acaoBean;
	}


}
