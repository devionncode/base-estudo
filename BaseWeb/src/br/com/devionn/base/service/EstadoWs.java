package br.com.devionn.base.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.EstadoBeanLocal;
import br.com.devionn.model.Estado;

@Stateless
@Path("estado")
public class EstadoWs extends AbstractWs<Estado>{
	
	@EJB
	private EstadoBeanLocal estadoBean;
	
	@Override
	public AbstractBean<Estado> getDAS() {
		return estadoBean;
	}

	@GET
	@Path("findpornomeidpais")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findPorNomeEIdPais(@Context HttpHeaders headers,@QueryParam("idpais")Long idPais, @QueryParam("nome")String nome) throws Exception {
    	List<Estado> lista = estadoBean.findPorNome(getUsuario(headers), idPais, nome); 
        return Response.ok(toJson(lista)).build();
    } 
	
	@Path("/findpornome")
    @POST
    @Produces(MediaType.APPLICATION_JSON)	
	public Response findPorNome(@Context HttpHeaders headers, String nome ) throws Exception {
		List<Estado> lista = estadoBean.findPorNome(getUsuario(headers), nome);  
        return Response.ok(toJson(lista)).build();
	}
}
