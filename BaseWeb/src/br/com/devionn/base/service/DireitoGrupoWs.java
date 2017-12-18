package br.com.devionn.base.service;

import java.util.Map;

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
import br.com.devionn.das.DireitoGrupoBeanLocal;
import br.com.devionn.model.DireitoGrupo;
import br.com.devionn.result.PermissaoAcesso;

@Stateless
@Path("direitogrupo")
public class DireitoGrupoWs extends AbstractWs<DireitoGrupo>{
	
	@EJB
	private DireitoGrupoBeanLocal direitoGrupoBean;
	
	@Override
	public AbstractBean<DireitoGrupo> getDAS() {
		return direitoGrupoBean;
	}
	
	@GET
    @Path("findbyidtela/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByIdTela(@Context HttpHeaders headers,@PathParam("id") Long id) throws Exception{
    	String obj = toJson(direitoGrupoBean.findByIdTela(getUsuario(headers),id)); 
        return Response.ok(obj).build();
    }
	
	@POST
    @Path("getambiente")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAmbiente(@Context HttpHeaders headers) throws Exception{
    	Map<String, Object> map = direitoGrupoBean.getAmbienteByUsuario(getUsuario(headers)); 
        return Response.ok(toJson(map)).build();
    }
	
	
	@POST
    @Path("salvardireitos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response salvarDireitos(@Context HttpHeaders headers, PermissaoAcesso permissao) throws Exception{
    	direitoGrupoBean.salvarDireitos(getUsuario(headers), permissao); 
        return Response.ok().build();
    }

}
