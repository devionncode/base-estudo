package br.com.devionn.das;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Cidade;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class CidadeBean
 */
@Stateless
@LocalBean
public class CidadeBean extends AbstractBeanImpl<Cidade> implements CidadeBeanLocal {

    /**
     * Default constructor. 
     */
    public CidadeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Cidade.class;
	}

	@Override
	public List<Cidade> getPorUf(String Uf) {
    	Query query = entity.createQuery("Select c from Cidade c where upper(c.estado.uf) = :uf ");
    	query.setParameter("uf", Uf.toUpperCase().trim());
		return query.getResultList();
	}

	@Override
	public List<Cidade> getPorIdEstado(Long idEstado) {
		Query query = entity.createQuery("Select c from Cidade c where c.estado.id = :id ");
    	query.setParameter("id", idEstado);
		return query.getResultList();
	} 
	
	public List<Cidade> findPorNomeEIdEstado(Usuario usuario, String nome,Long idEstado) throws Exception {
		String sql = "Select c From Cidade c where upper(c.nome) like :nome and c.estado.id = :idEstado ";
		Query query = entity.createQuery(sql, Cidade.class);
		query.setParameter("nome", "%" + nome.trim().toUpperCase() + "%");
		query.setParameter("idEstado", idEstado);
		query.setMaxResults(30);
		return query.getResultList();
	}
	
	public List<Cidade> findPorNome(Usuario usuario, Long idEstado, String nome) throws Exception{
		Query query = entity.createQuery("Select c From Cidade c where c.deletado = false and c.estado.id = :idEstado and upper(c.nome) like :nome  order by c.nome ");
		query.setParameter("idEstado", idEstado);
		query.setParameter("nome", "%"+nome.trim().toUpperCase()+"%");		
		return query.getResultList();
	}
}
