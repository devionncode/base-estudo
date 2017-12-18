package br.com.devionn.das;

import javax.ejb.Local;

import br.com.devionn.model.ContaEmail;
import br.com.devionn.model.Usuario;
@Local
public interface ContaEmailBeanLocal extends AbstractBean<ContaEmail> {
	
	public ContaEmail getContaEmail(Usuario usuario) throws Exception;

}


