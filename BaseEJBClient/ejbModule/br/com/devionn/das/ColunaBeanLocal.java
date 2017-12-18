package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Coluna;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.ColunaResult;

@Local
public interface ColunaBeanLocal extends AbstractBean<Coluna>{

	public List<Coluna> findByIdFiltro(Long idFiltro) throws Exception;
	
	public List<ColunaResult> findBySistema(Usuario usuario,Integer sistema) throws Exception;
}
