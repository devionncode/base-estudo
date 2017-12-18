package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Acao;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.AcaoAcesso;

@Local
public interface AcaoBeanLocal extends AbstractBean<Acao>{

	public List<AcaoAcesso> findByDireito(Usuario usuario, Integer sistema) throws Exception;
}
