package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.TelaAcaoBeanLocal;
import br.com.devionn.model.TelaAcao;

@Stateless
@Path("telaAcao")
public class TelaAcaoWs extends AbstractWs<TelaAcao> {
	@EJB
	private TelaAcaoBeanLocal telaAcaoBean;
	
	@Override
	public AbstractBean<TelaAcao> getDAS() {
		return telaAcaoBean;

    }

}
