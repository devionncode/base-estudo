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
import br.com.devionn.das.SistemaBeanLocal;
import br.com.devionn.model.Sistema;

@Stateless
@Path("sistema")
public class SistemaWs extends AbstractWs<Sistema>  {
	
	@EJB
	private SistemaBeanLocal sistemaBean;
	
	@Override
	public AbstractBean<Sistema> getDAS() {
		return sistemaBean;
    }
	
	@GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response find(@Context HttpHeaders headers) throws Exception{
    	String obj = toJson(sistemaBean.getSistema(getUsuario(headers))); 
        return Response.ok(obj).build();
    }
}