package br.com.devionn.das;

import javax.ejb.Local;


import br.com.devionn.model.EmailUsuario;
import br.com.devionn.model.Mail;
import br.com.devionn.model.Usuario;

@Local
public interface EmailUsuarioBeanLocal extends AbstractBean<EmailUsuario>{
	
	public EmailUsuario getEmailUsuario(Usuario usuario) throws Exception;
	
	public Boolean enviar(Usuario usuario, Mail mail) throws Exception;

}
