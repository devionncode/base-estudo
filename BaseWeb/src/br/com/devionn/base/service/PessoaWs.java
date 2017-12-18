package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.PessoaBeanLocal;
import br.com.devionn.model.Pessoa;

@Stateless
@Path("pessoa")
public class PessoaWs extends AbstractWs<Pessoa>  {
	
	@EJB
	private PessoaBeanLocal pessoaBean;
	
	@Override
	public AbstractBean<Pessoa> getDAS() {
		return pessoaBean;
	}
	

}
