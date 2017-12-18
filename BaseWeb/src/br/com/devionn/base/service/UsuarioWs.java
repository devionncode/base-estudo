/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.base.service;
 
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.UsuarioBeanLocal;
import br.com.devionn.gson.GsonIgnore;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.Login;
import br.com.devionn.result.UserAcesso;

/**
 *
 * @author anderson
 */
@Stateless
@Path("usuario")
public class UsuarioWs extends AbstractWs<Usuario> {

    @EJB
    private UsuarioBeanLocal usuarioBean; 
    
    public UsuarioWs() {
    }

    @Override
    public AbstractBean<Usuario> getDAS() {
        return usuarioBean;
    }
    
    @Override
	public GsonBuilder getGsonBuilder() {
		return super.getGsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
			
			@Override
			public boolean shouldSkipField(FieldAttributes f) { 
				GsonIgnore gson = f.getAnnotation(GsonIgnore.class);
				if ( f.getName().equals("enderecos")){
					return true;
				}
				if (gson != null){
					return true;
				}
				return false;
			}
			
			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				GsonIgnore gson = clazz.getDeclaredAnnotation(GsonIgnore.class);
				if (gson != null){
					return true;
				}
				return false;
			}
		});
	}
 
    @Path("/logar")
    @POST 
    @Produces({MediaType.APPLICATION_JSON})
    public String logar(@Context HttpServletRequest request, Login login) throws Exception{
    	UserAcesso user = usuarioBean.getByLongin(login);
    	request.setAttribute("usuario", user);
    	if (request.getAttribute("usuario") != null) {
    		
    	}
        return toJson(user);
    }
    
    @Path("/buscarporcodigoourazaosocial")
    @POST 
    @Produces({MediaType.APPLICATION_JSON})
    public String buscarPorCodigoOuRazaoSocial(@Context HttpHeaders headers, String nome) throws Exception{
    	 List<Usuario> lista = usuarioBean.buscarPorCodigoOuRazaoSocial(getUsuario(headers), nome);
        return toJson(lista);
    }
    
    @Path("/alterarsenha")
    @POST 
    @Produces({MediaType.APPLICATION_JSON})
    public Response alterarSenha(@Context HttpHeaders headers, Login usuario) throws Exception{
    	try {
	    	usuario = usuarioBean.alterarSenha( getUsuario(headers), usuario);
	        return sendOk(usuario); 
    	}catch (Exception e) {
			return sendError(e.getLocalizedMessage());
		}
    } 
    
    @GET
	@Path("findporusuario")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findPorUsuario(@Context HttpHeaders headers) throws Exception { 
        return Response.ok(toJson(getUsuario(headers))).build();
    } 
    
    
    @Path("/existelogin/{idUsuario}/{login}")
    @POST 
    @Produces({MediaType.APPLICATION_JSON})
    public Response existeLogin(@Context HttpHeaders headers,@PathParam("idUsuario")Long idUsuario, @PathParam("login") String login ) throws Exception{
    	try {
	    	Boolean existe = usuarioBean.existeLogin( getUsuario(headers), login, idUsuario );
	    	return Response.ok(existe).build(); 
    	}catch (Exception e) {
			return sendError(e.getLocalizedMessage());
		}
    } 
    
    @POST
	@Path("logout")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response logout(@Context HttpHeaders headers) throws Exception { 
        return Response.ok(toJson(new Object())).build();
    }
}
