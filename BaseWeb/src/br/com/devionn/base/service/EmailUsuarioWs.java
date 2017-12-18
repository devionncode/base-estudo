package br.com.devionn.base.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.EmailUsuarioBeanLocal;
import br.com.devionn.model.ContaEmail;
import br.com.devionn.model.EmailUsuario;
import br.com.devionn.model.Mail;

@Stateless
@Path("emailusuario")
public class EmailUsuarioWs extends AbstractWs<EmailUsuario>{
	
	@EJB
	private EmailUsuarioBeanLocal emailUsuarioBean;
	
	@Override
	public AbstractBean<EmailUsuario> getDAS() {
		return emailUsuarioBean;
	}
	
	@POST
	@Path("enviar")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response enviar(@Context HttpHeaders headers,Mail mail) throws Exception {
    	emailUsuarioBean.enviar(getUsuario(headers), mail);
        return Response.ok(toJson(mail)).build();
    } 

	@GET
	@Path("findemailusuario")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findEmailUsuario(@Context HttpHeaders headers) throws Exception{
		EmailUsuario config = emailUsuarioBean.getEmailUsuario(getUsuario(headers));
    	return Response.ok(toJson(config)).build();
    }

}
