package br.com.devionn.das;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Estado;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class EstadoBean
 */
@Stateless
public class EstadoBean extends AbstractBeanImpl<Estado> implements EstadoBeanLocal,EstadoBeanRemote  {

    public EstadoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Estado.class;
		
	}

	public List<Estado> findPorNome(Usuario usuario, String nome) throws Exception {
		String sql = "Select e From Estado e where e.deletado = false and ( upper(e.nome) like :nome or upper(e.uf) = :uf ) order by e.nome asc ";
		Query query = entity.createQuery(sql, Estado.class);
		query.setParameter("nome", "%"+ nome.trim().toUpperCase() + "%");
		query.setParameter("uf", nome.toUpperCase().trim());
		return query.getResultList();
	}
	
	public List<Estado> findPorNome(Usuario usuario, Long idPais, String nome) throws Exception{
		Query query = entity.createQuery("Select e From Estado e where e.deletado = false and e.pais.id = :idPais and ( upper(e.nome) like :nome or upper(e.uf) = :uf ) order by e.nome ");
		query.setParameter("idPais", idPais);
		query.setParameter("nome", "%"+nome.trim().toUpperCase()+"%");
		query.setParameter("uf", nome.trim().toUpperCase());
		return query.getResultList();
	}
	
	
}
