package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.TelaAcao;

/**
 * Session Bean implementation class TelaAcaoBean
 */
@Stateless
@LocalBean
public class TelaAcaoBean extends AbstractBeanImpl<TelaAcao> implements TelaAcaoBeanLocal, TelaAcaoBeanRemote {

    /**
     * Default constructor. 
     */
    public TelaAcaoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return TelaAcao.class;
	}

	@Override
	public Boolean insertNativo(TelaAcao obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into TelaAcao (id,dataalteracao,datacadastro,deletado, versao,idacao,idtela,posicao)  "
				+ "  values (?, now(),now(),false , 0, ?, ?, ? ); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getAcao().getId());
    	query.setParameter(3, obj.getTela().getId());
    	query.setParameter(4, obj.getPosicao());
    	query.executeUpdate();
		return true;
	}
}
