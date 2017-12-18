package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Pais;
import br.com.devionn.model.Usuario;

@Local
public interface PaisBeanLocal extends AbstractBean<Pais>{

	public List<Pais> findPorNome(Usuario usuario, String nome) throws Exception;
}
