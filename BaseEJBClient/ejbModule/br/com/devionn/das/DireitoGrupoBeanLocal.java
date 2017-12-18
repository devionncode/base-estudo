package br.com.devionn.das;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.devionn.model.Direito;
import br.com.devionn.model.DireitoGrupo;
import br.com.devionn.model.GrupoUsuario;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.PermissaoAcesso;



@Local
public interface DireitoGrupoBeanLocal extends AbstractBean<DireitoGrupo> {

	public List<Direito> findByIdTela(Usuario usuario, Long idTela) throws Exception;
	
	public Map<String,Object> getAmbienteByUsuario(Usuario usuario) throws Exception;
	
	public List<DireitoGrupo> getByIdGrupoUsuario(Usuario usuario, Long idGrupoUsuario) throws Exception;
	
	public List<Direito> getByIdGrupoUsuarioIdTela(Usuario usuario, Long idGrupoUsuario, Long idTela) throws Exception;
	
	public Boolean salvarDireitos(Usuario usuario, PermissaoAcesso permissao)throws Exception;
	
	public void gerarDireitos(Usuario usuario)throws Exception;
	
	public void gerarDireitos(Usuario usuario, GrupoUsuario grupo) throws Exception;
}
