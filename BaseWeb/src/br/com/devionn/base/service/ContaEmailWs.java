package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.ContaEmailBeanLocal;
import br.com.devionn.model.ContaEmail;

@Stateless
@Path("contaemail")
public class ContaEmailWs extends AbstractWs<ContaEmail> {
	
	@EJB
	private ContaEmailBeanLocal contaEmailBean;
	
	@Override
	public AbstractBean<ContaEmail> getDAS() {
		return contaEmailBean;
	}
	
	@GET
	@Path("findcontaemail")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findContaEmail(@Context HttpHeaders headers) throws Exception{
		ContaEmail config = contaEmailBean.getContaEmail(getUsuario(headers));
    	return Response.ok(toJson(config)).build();
    }
	
}



