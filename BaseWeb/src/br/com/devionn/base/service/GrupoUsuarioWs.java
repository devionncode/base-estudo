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
import br.com.devionn.das.GrupoUsuarioBeanLocal;
import br.com.devionn.model.GrupoUsuario;

@Stateless
@Path("grupousuario")
public class GrupoUsuarioWs extends AbstractWs<GrupoUsuario>{
	
	@EJB
	private GrupoUsuarioBeanLocal grupoUsuarioBean;
	
	@Override
	public AbstractBean<GrupoUsuario> getDAS() {
		return grupoUsuarioBean;
	}
	
	@GET
    @Path("findparadireitosacesso")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findParaDireitosAcesso(@Context HttpHeaders headers) throws Exception{
    	String obj = toJson(grupoUsuarioBean.findParaDireitosAcesso( getUsuario(headers) )); 
        return Response.ok(obj).build();
    }
	
	

}
