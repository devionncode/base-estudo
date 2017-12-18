package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Idioma;

/**
 * Session Bean implementation class IdiomaBean
 */
@Stateless
@LocalBean
public class IdiomaBean extends AbstractBeanImpl<Idioma> implements IdiomaBeanLocal {

    /**
     * Default constructor. 
     */
    public IdiomaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Idioma.class;
	}

	@Override
	public Boolean insertNativo(Idioma obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into Idioma (id,dataalteracao,datacadastro,deletado, versao,nome, coluna )  "
				+ "  values (?, now(),now(),false , 0, ?, ? ); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getNome());
    	query.setParameter(3, obj.getColuna()); 
    	query.executeUpdate();
		return super.insertNativo(obj);
	}
}
