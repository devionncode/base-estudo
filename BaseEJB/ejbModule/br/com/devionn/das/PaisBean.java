package br.com.devionn.das;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Pais;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class PaisBean
 */
@Stateless(name="PaisBeanBase")
public class PaisBean extends AbstractBeanImpl<Pais> implements PaisBeanLocal, PaisBeanRemote {

    /**
     * Default constructor. 
     */
    public PaisBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Pais.class;
	}

	public List<Pais> findPorNome(Usuario usuario, String nome) throws Exception{
		Query query = entity.createQuery("Select p From Pais p where p.deletado = false and ( upper(p.nome) like :nome or upper(p.sigla) = :sigla ) order by p.nome ");
		query.setParameter("nome", "%"+nome.trim().toUpperCase()+"%");
		query.setParameter("sigla", nome.trim().toUpperCase());
		return query.getResultList();
	}
}
