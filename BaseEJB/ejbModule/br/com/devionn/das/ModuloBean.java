package br.com.devionn.das;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Modulo;

/**
 * Session Bean implementation class ModuloBean
 */
@Stateless
@LocalBean
public class ModuloBean extends AbstractBeanImpl<Modulo> implements ModuloBeanLocal {

    /**
     * Default constructor. 
     */
    public ModuloBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Modulo.class;
	}
	
	public List<Modulo> getByIdEmpresa(Long idEmpresa)throws Exception{
		Query query = entity.createQuery("Select m From Modulo m where m.empresa.id = :idEmpresa and m.deletado = false ");
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();		
	}
	
	@Override
	public Boolean insertNativo(Modulo obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into modulo (id,dataalteracao,datacadastro,deletado, versao,ativo,descricao, mobile, posicao,principal)  "
				+ "  values (?, now(),now(),false,0,?,?,?, ?, ?);   ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getAtivo());
    	query.setParameter(3, obj.getDescricao());
    	query.setParameter(4, obj.getMobile());
    	query.setParameter(5, obj.getPosicao());
    	query.setParameter(6, obj.getPrincipal());
    	query.executeUpdate();
		return true;
	}
}
