package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Filtro;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.Filter;
import br.com.devionn.result.FiltroResult;
import br.com.devionn.result.RetornoFiltro;

@Local
public interface FiltroBeanLocal extends AbstractBean<Filtro> {

	public RetornoFiltro gerarColunas(Usuario usuario,Filtro filtro)throws Exception;
	
	public Filtro getConfiguracao(Usuario usuario,Long idFiltro) throws Exception;
	
	public Filtro getConfiguracaoPorTela(Usuario usuario, Long idTela) throws Exception;
	
	public Filter getByFilter(Usuario usuario,Filter filter) throws Exception;
	
	public List<FiltroResult> findBySistema(Usuario usuario,Integer sistema) throws Exception;
	
}
