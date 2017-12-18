package br.com.devionn.das;



import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Cidade;
import br.com.devionn.model.Usuario;

@Local
public interface CidadeBeanLocal extends AbstractBean<Cidade> {
	
	public List<Cidade>getPorUf(String Uf);
	
	public List<Cidade>getPorIdEstado(Long idEstado);
	
	public List<Cidade> findPorNomeEIdEstado(Usuario usuario, String nome,Long idEstado) throws Exception;
	
	public List<Cidade>findPorNome(Usuario usuario, Long idPaid, String nome) throws Exception;

}
