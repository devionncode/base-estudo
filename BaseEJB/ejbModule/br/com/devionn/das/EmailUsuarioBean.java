package br.com.devionn.das;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.ContaEmail;
import br.com.devionn.model.EmailUsuario;
import br.com.devionn.model.Mail;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class EmailUsuarioBean
 */
@Stateless
@LocalBean
public class EmailUsuarioBean extends AbstractBeanImpl<EmailUsuario> implements EmailUsuarioBeanLocal {
       
    /**
     * @see AbstractBeanImpl#AbstractBeanImpl()
     */
	@EJB
	private UsuarioBeanLocal usuarioBean;
	@EJB
	private EmailBeanLocal emailBem;
    public EmailUsuarioBean() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return EmailUsuario.class;
		
	}
	
	public Boolean enviar(Usuario usuario, Mail mail) throws Exception {
		EmailUsuario conta = getEmailUsuario(usuario);
		emailBem.sendEmail(conta, mail);
		return true;	
		
	}
	
	@Override
	public EmailUsuario save(Usuario usuario, EmailUsuario obj) throws Exception {
		obj.setUsuario(usuarioBean.find(usuario, obj.getUsuario().getId()));
		return super.save(usuario, obj);
	}
	
	public EmailUsuario getEmailUsuario(Usuario usuario) throws Exception {
		Query query = entity.createQuery("Select c From EmailUsuario c where c.deletado = false and c.usuario.id = :idUsuario ");
		query.setParameter("idUsuario", usuario.getId());
		List<EmailUsuario> lista = query.getResultList();
		if (lista.isEmpty()){
			return null;
		}
		return lista.get(0);
	}
	
}
