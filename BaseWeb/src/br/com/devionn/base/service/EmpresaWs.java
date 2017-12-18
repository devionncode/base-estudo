package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.EmpresaBeanLocal;
import br.com.devionn.model.Empresa;
import br.com.devionn.result.NovoAmbiente;

@Stateless
@Path("empresa")
public class EmpresaWs extends AbstractWs<Empresa>{

	@EJB
	private EmpresaBeanLocal empresaBean;
	
	@Override
	public AbstractBean<Empresa> getDAS() {
		return empresaBean;
	}


    @GET
    @Path("findporusuario")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response findPorUsuario(@Context HttpHeaders headers) throws Exception{
    	String obj = toJson(empresaBean.findPorUsuario(getUsuario(headers))); 
        return Response.ok(obj).build();
    }
    
    @POST
    @Path("novoambiente")
    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
    public Response novoAmbiente(@Context HttpHeaders headers, NovoAmbiente novoAmbiente) throws Exception {
    	empresaBean.novoAmbiente(getUsuario(headers), novoAmbiente);
    	return Response.ok().build(); 
    }
	
}
