package br.com.devionn.das;

import javax.ejb.Remote;

import br.com.devionn.model.Dispositivo;
import br.com.devionn.model.Usuario;

@Remote
public interface DispositivoBeanRemote extends AbstractBean<Dispositivo> {

	public Dispositivo buscarPor(String plataforma,String serial, String uuid) throws Exception;
	
	public Dispositivo buscarPorToken(Usuario usuario, String token) throws Exception;
}
