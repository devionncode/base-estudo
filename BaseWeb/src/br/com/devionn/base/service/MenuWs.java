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
import br.com.devionn.das.MenuBeanLocal;
import br.com.devionn.model.Menu;
import br.com.devionn.model.Usuario;

@Stateless
@Path("menu")
public class MenuWs extends AbstractWs<Menu>{

	@EJB
	private MenuBeanLocal menuBean;
	
	@Override
	public AbstractBean<Menu> getDAS() {
		return menuBean;
	}
		
	@GET
    @Path("findmenusporacesso")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findMenusPorAcesso(@Context HttpHeaders headers) throws Exception{
		Usuario usuario = getUsuario(headers);
    	String obj = toJson(menuBean.findMenusPorAcesso(usuario,1)); 
        return Response.ok(obj).build();
    }
	
}

