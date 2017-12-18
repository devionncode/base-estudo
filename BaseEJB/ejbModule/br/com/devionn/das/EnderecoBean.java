package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.devionn.model.Acao;
import br.com.devionn.model.Endereco;

/**
 * Session Bean implementation class EnderecoBean
 */
@Stateless
@LocalBean
public class EnderecoBean extends AbstractBeanImpl<Endereco> implements EnderecoBeanLocal {

    /**
     * Default constructor. 
     */
    public EnderecoBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public Class getClasse() {
		return Endereco.class;
	}

}
