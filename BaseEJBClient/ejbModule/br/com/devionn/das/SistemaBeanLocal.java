package br.com.devionn.das;

import javax.ejb.Local;

import br.com.devionn.model.Sistema;
import br.com.devionn.model.Usuario;

@Local
public interface SistemaBeanLocal extends AbstractBean<Sistema> {

	public Sistema getSistema(Usuario usuario) throws Exception;
}
