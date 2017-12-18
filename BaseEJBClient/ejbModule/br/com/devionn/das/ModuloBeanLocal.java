package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Modulo;

@Local
public interface ModuloBeanLocal  extends AbstractBean<Modulo>{

	public List<Modulo> getByIdEmpresa(Long idEmpresa)throws Exception;
}
