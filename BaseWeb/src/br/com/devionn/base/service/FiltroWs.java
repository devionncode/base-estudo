package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.FiltroBeanLocal;
import br.com.devionn.das.UsuarioBeanRemote;
import br.com.devionn.model.Filtro;
import br.com.devionn.result.Filter;

@Stateless
@Path("filtro")
public class FiltroWs extends AbstractWs<Filtro> {
	
	@EJB
	private FiltroBeanLocal filtroBean;
	
	@EJB
	private UsuarioBeanRemote usuarioBean;
	
	@Override
	public AbstractBean<Filtro> getDAS() {
		return filtroBean;
	}
	
	public FiltroWs() {
		ignorarDependecias(new String[]{"filtro"});
	}
	
	@Path("/filter")
	@POST
	@Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
//	@Consumes(value=MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8")
	public String filter(@Context HttpHeaders headers,Filter filter) {
		try {			
			filter = filtroBean.getByFilter(getUsuario(headers), filter);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return toJson(filter);
	}

	@Path("getconfiguracao/{id}")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getConfiguracao(@Context HttpHeaders headers,@PathParam("id") Long idFiltro)  {
		try {			 	
			return Response.ok(toJson(filtroBean.getConfiguracao(getUsuario(headers), idFiltro))).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
		}
	}
	
	@Path("getconfiguracaoportela/{id}")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getConfiguracaoPorTela(@Context HttpHeaders headers,@PathParam("id") Long idFiltro)  {
		try {			 	
			return Response.ok(toJson(filtroBean.getConfiguracaoPorTela(getUsuario(headers), idFiltro))).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
		}
	}
	
	
	@Path("/gerarcolunas")
	@POST
	@Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8") 
	public Response gerarColunas(@Context HttpHeaders headers,Filtro filtro) {
		try {			
			return Response.ok(toJson(filtroBean.gerarColunas(getUsuario(headers), filtro))).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
		}
	}
}