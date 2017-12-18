package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Menu;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.MenuAcesso;

@Local
public interface MenuBeanLocal extends AbstractBean<Menu> {

	public List<Menu> getByIdModulo(Long idModulo) throws Exception;
	
	public List<MenuAcesso> findMenusPorAcesso(Usuario usuario, Integer sistema) throws Exception;
}
