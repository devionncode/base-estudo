package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.EnderecoBeanLocal;
import br.com.devionn.model.Endereco;

@Stateless
@Path("endereco")
public class EnderecoWs extends AbstractWs<Endereco> {
	
	
	@EJB
	private EnderecoBeanLocal enderecoBean;
	
	@Override
	public AbstractBean<Endereco> getDAS() {
		return enderecoBean;
	}

}
