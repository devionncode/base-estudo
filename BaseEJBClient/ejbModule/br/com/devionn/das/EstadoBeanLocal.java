package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Estado;
import br.com.devionn.model.Usuario;

@Local
public interface EstadoBeanLocal extends AbstractBean<Estado> {

	public List<Estado> findPorNome(Usuario usuario, String nome) throws Exception;
	
	public List<Estado> findPorNome(Usuario usuario, Long idPaid, String nome) throws Exception;
}
