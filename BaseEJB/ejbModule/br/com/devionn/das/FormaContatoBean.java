package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.devionn.model.FormaContato;

/**
 * Session Bean implementation class FormaContatoBean
 */
@Stateless
@LocalBean
public class FormaContatoBean extends AbstractBeanImpl<FormaContato> implements FormaContatoBeanLocal {

    /**
     * Default constructor. 
     */
    public FormaContatoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return FormaContato.class;
	}

}
