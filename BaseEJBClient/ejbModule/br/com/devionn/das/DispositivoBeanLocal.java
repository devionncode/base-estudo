package br.com.devionn.das;

import javax.ejb.Local;

import br.com.devionn.model.Dispositivo;
import br.com.devionn.model.Usuario;

@Local
public interface DispositivoBeanLocal extends AbstractBean<Dispositivo> {

	
	public Dispositivo buscarPor(String plataforma,String serial, String uuid) throws Exception;
	
	public Dispositivo buscarPorToken(Usuario usuario, String token) throws Exception;
}
