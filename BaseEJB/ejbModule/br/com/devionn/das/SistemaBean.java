package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.devionn.model.Sistema;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class SistemaBean
 */
@Stateless
@LocalBean
public class SistemaBean extends AbstractBeanImpl<Sistema> implements SistemaBeanLocal {
       
    /**
     * @see AbstractBeanImpl#AbstractBeanImpl()
     */
    public SistemaBean() {
        super(); 
    }

	@Override
	public Class getClasse() {
		return Sistema.class;
	}

	public Sistema getSistema(Usuario usuario) throws Exception {
		return find(usuario, 1L);
	}
	

}
