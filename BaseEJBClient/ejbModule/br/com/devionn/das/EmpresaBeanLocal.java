package br.com.devionn.das;

import javax.ejb.Local;

import br.com.devionn.model.Empresa;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.NovoAmbiente;

@Local
public interface EmpresaBeanLocal extends AbstractBean<Empresa>{

	public Empresa findPorUsuario(Usuario usuario)throws Exception;
	
	public Boolean novoAmbiente(Usuario usuario, NovoAmbiente novoAmbiente) throws Exception;
}
