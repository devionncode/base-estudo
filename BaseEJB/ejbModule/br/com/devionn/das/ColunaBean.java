package br.com.devionn.das;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Coluna;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.ColunaResult;

/**
 * Session Bean implementation class ConfiguraFiltro
 */
@Stateless
@LocalBean
public class ColunaBean extends AbstractBeanImpl<Coluna> implements ColunaBeanLocal {

    /**
     * Default constructor. 
     */
    public ColunaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class<Coluna> getClasse() {
		return Coluna.class;
	}
	
	public List<Coluna> findByIdFiltro(Long idFiltro) throws Exception{
//		String sql = "Select 	"
//				+ "	c.id, c.titulo, c.dataAlteracao, c.dataCadastro, c.deletado, c.versao, c.idfiltro, c.visivel, c.sortOrder, c.sortable, c.filtroPadrao, c.largura, c.posicao, c.tipo, c.variavel, c.propriedade  "
//				+ "From "
//				+ "		Coluna c "
//				+ "		inner join filtro f on c.idfiltro = f.id where f.id = ? and c.deletado = false order by c.posicao asc ";
//		Query query = entity.createNativeQuery(sql,Coluna.class);
//		query.setParameter(1, idFiltro);
		String sql = "Select c  From "
				+ "		Coluna c "
				+ "		where c.filtro.id = :codigo and c.deletado = false order by c.posicao asc ";
		Query query = entity.createQuery(sql,Coluna.class);
		query.setParameter("codigo", idFiltro);
		
		return query.getResultList();
	}

	public List<ColunaResult> findBySistema(Usuario usuario,Integer sistema) throws Exception {
		String sql = "select                                         "
				+ "		cl.id, cl.filtroPadrao, cl.idfiltro, cl.largura, cl.posicao, cl.propriedade, cl.sortorder, cl.sortable,cl.tipo,cl.titulo, cl.variavel,cl.visivel,cl.alinhamento, cl.mascara    "
				+ "	from                 "
				+ "		public.coluna cl        "
				+ "		inner join public.filtro f on cl.idfiltro = f.id             "
				+ "		inner join public.tela t on t.id = f.idtela                  "
				+ "	where                     	  "
				+ " 	f.deletado = false and (t.sistema = ? or t.sistema = 3)   ";
		Query query = entity.createNativeQuery(sql, ColunaResult.class);
		query.setParameter(1, sistema);
		return query.getResultList();
	}
	
}
