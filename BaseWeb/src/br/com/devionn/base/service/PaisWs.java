package br.com.devionn.base.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.PaisBeanLocal;
import br.com.devionn.model.Pais;

@Stateless
@Path("pais")
public class PaisWs extends AbstractWs<Pais> {

	@EJB
	private PaisBeanLocal paisBean;
	
	@Override
	public AbstractBean<Pais> getDAS() {
		return paisBean;
	}

	@GET
	@Path("findpornome")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findPorNome(@Context HttpHeaders headers,@QueryParam("nome")String nome) throws Exception {
    	List<Pais> lista = paisBean.findPorNome(getUsuario(headers), nome); 
        return Response.ok(toJson(lista)).build();
    } 
}