package br.com.devionn.base.service;

import java.util.List;

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
import br.com.devionn.das.TelaBeanLocal;
import br.com.devionn.model.Tela;
import br.com.devionn.model.Usuario;

@Stateless
@Path("tela")
public class TelaWs extends AbstractWs<Tela> {
	
	@EJB	private TelaBeanLocal telaBean;
	
	@Override
	public AbstractBean<Tela> getDAS() {
		return telaBean;

    }

	@GET
    @Path("findtelasporacesso/{idmenu}")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findTelasPorAcesso(@Context HttpHeaders headers, @PathParam("idmenu")Long idMenu) throws Exception{
    	String obj = toJson(telaBean.findTelasPorAcesso(getUsuario(headers), idMenu )); 
        return Response.ok(obj).build();
    }
	
	@POST
    @Path("findpornome")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findPorNome(@Context HttpHeaders headers, String nome) throws Exception{
    	String obj = toJson(telaBean.findPorNome(getUsuario(headers), nome )); 
        return Response.ok(obj).build();
    }
	
	@GET
    @Path("findtelaspormenu/{idmenu}")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findTelasPorMenu(@Context HttpHeaders headers, @PathParam("idmenu")Long idMenu) throws Exception{
    	String obj = toJson(telaBean.findTelasPorMenu(getUsuario(headers), idMenu )); 
        return Response.ok(obj).build();
    } 
	
	@GET
    @Path("buscartelascomacoespormenu/{idmenu}/{idGrupoUsuario}")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response buscarTelasComAcoesPorMenu(@Context HttpHeaders headers, @PathParam("idmenu")Long idMenu,@PathParam("idGrupoUsuario")Long idGrupoUsuario) throws Exception{
    	String obj = toJson(telaBean.buscarTelasComAcoesPorMenu(getUsuario(headers), idGrupoUsuario, idMenu)); 
        return Response.ok(obj).build();
    } 
	
}
