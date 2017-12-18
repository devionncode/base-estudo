package br.com.devionn.base.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.TranslateBeanLocal;
import br.com.devionn.model.Translate;
import sun.security.provider.certpath.OCSPResponse.ResponseStatus;

@Stateless
@Path("translate")
public class TranslateWs extends AbstractWs<Translate>{
	
	@EJB
	private TranslateBeanLocal translateBean;
	
	@Override
	public AbstractBean<Translate> getDAS() {
		return translateBean;

    }
	
	@Path("/getbyidioma")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getByIdioma(@QueryParam("idioma") String idioma) {
		try {
			return sendOk(translateBean.getByIdioma(idioma));
		} catch (Exception e) {
			return sendError(e.getMessage());
		}
	}

}
