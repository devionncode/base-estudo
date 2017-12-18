package br.com.devionn.das;

import javax.ejb.Remote;

import br.com.devionn.model.ContaEmail;
import br.com.devionn.model.Usuario;

@Remote
public interface ContaEmailBeanRemote extends AbstractBean<ContaEmail> {
	
	public ContaEmail getContaEmail(Usuario usuario) throws Exception;

}


