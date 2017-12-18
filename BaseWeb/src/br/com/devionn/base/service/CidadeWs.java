package br.com.devionn.base.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.CidadeBeanLocal;
import br.com.devionn.model.Cidade;


@Stateless
@Path("cidade")
public class CidadeWs extends AbstractWs <Cidade> {
	
	@EJB
	private CidadeBeanLocal cidadeBean;
	
	 public CidadeWs() {
	    } 
	
	@Override												
	public AbstractBean<Cidade> getDAS() {
		return cidadeBean;
	}
	
	@Path("/getporuf")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPorUf(@QueryParam("uf") String uf) throws Exception{ 
    	List<Cidade> lista = cidadeBean.getPorUf(uf); 
    			
        return Response.ok(toJson(lista)).build();
    }

	@Path("/getporidestado/{idestado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPorIdEstado(@PathParam("idestado") Long id) throws Exception{ 
    	List<Cidade> lista = cidadeBean.getPorIdEstado(id); 
    			
        return Response.ok(toJson(lista)).build();
    }
	
	@GET
	@Path("findpornome")
    @Produces(MediaType.APPLICATION_JSON)
	public Response findPorNome(@Context HttpHeaders headers,@QueryParam("idestado") Long idEstado, @QueryParam("nome")String nome) throws Exception {
    	List<Cidade> lista = cidadeBean.findPorNome(getUsuario(headers),  idEstado, nome); 
        return Response.ok(toJson(lista)).build();
    } 
	
	@Path("/findpornomeeidestado/{idEstado}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)	
	public Response findPorNomeEIdEstado(@Context HttpHeaders headers, String nome,@PathParam("idEstado") Long idEstado) throws Exception {
		List<Cidade> lista = cidadeBean.findPorNomeEIdEstado(getUsuario(headers), nome, idEstado);  
        return Response.ok(toJson(lista)).build();
	}
}
