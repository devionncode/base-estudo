package br.com.devionn.das;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.ContaEmail;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class EmailUsuarioBean
 */
@Stateless 
public class ContaEmailBean extends AbstractBeanImpl<ContaEmail> implements ContaEmailBeanLocal, ContaEmailBeanRemote {
       
    /**
     * @see AbstractBeanImpl#AbstractBeanImpl()
     */
    public ContaEmailBean() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return ContaEmail.class;
		
	}
	
	public ContaEmail getContaEmail(Usuario usuario) throws Exception {
		Query query = entity.createQuery("Select c From ContaEmail c where c.deletado = false and c.idEmpresa = :idEmpresa ");
		query.setParameter("idEmpresa", usuario.getIdEmpresa());
		List<ContaEmail> lista = query.getResultList();
		if (lista.isEmpty()){
			return null;
		}
		return lista.get(0);
	}

}
