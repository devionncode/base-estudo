package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.devionn.model.VariavelFiltro;

/**
 * Session Bean implementation class VariavelFiltroBean
 */
@Stateless
@LocalBean
public class VariavelFiltroBean extends AbstractBeanImpl<VariavelFiltro> implements VariavelFiltroBeanLocal {

    /**
     * Default constructor. 
     */
    public VariavelFiltroBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return VariavelFiltro.class;
	}

}
