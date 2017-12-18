package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.GrupoUsuario;
import br.com.devionn.model.Usuario;

@Local
public interface GrupoUsuarioBeanLocal extends AbstractBean<GrupoUsuario>{

	public List<GrupoUsuario> findParaDireitosAcesso(Usuario usuario)throws Exception;
	
}