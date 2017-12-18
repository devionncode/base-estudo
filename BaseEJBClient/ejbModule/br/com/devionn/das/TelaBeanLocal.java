package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Tela;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.TelaAcesso;

@Local
public interface TelaBeanLocal extends AbstractBean<Tela> {

	public List<Tela> findTelasPorAcesso(Usuario usuario, Long idMenu) throws Exception;
	
	public List<TelaAcesso> findTelasPorAcesso(Usuario usuario,Integer sistema) throws Exception ;
	
	public List<Tela> findPorNome(Usuario usuario, String nome) throws Exception;
	
	public List<Tela> findTelasPorMenu(Usuario usuario, Long idMenu) throws Exception;
	
	public List<Tela> buscarTelasComAcoesPorMenu(Usuario usuario,Long idGrupoUsuario, Long idMenu) throws Exception;
}
