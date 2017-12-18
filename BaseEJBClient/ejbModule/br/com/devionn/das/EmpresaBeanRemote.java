package br.com.devionn.das;

import javax.ejb.Remote;

import br.com.devionn.model.Empresa;
import br.com.devionn.model.Usuario;

@Remote
public interface EmpresaBeanRemote extends AbstractBean<Empresa>{

	public Empresa findPorUsuario(Usuario usuario)throws Exception;
}
