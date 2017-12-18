package br.com.devionn.das;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.com.devionn.model.Tenant;

/**
 * Session Bean implementation class TenantBean
 */
@Stateless
public class TenantBean extends AbstractBeanImpl<Tenant> implements TenantBeanRemote {
       
    /**
     * @see AbstractBeanImpl#AbstractBeanImpl()
     */
    public TenantBean() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Tenant.class;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public void createSchema(Tenant tenant)throws Exception {
		entity.createNativeQuery("CREATE SCHEMA IF NOT EXISTS  "+ tenant.getSchemaName() + " ;").executeUpdate();
    	entity.flush();
	}
	
	 
	public Tenant getTenantPorToken(String token)throws Exception {
		Query query = entity.createNativeQuery("Select t.* from public.Tenant t  " 
				+ "   	inner join public.empresa emp on t.id = emp.idtenant    " 
				+ " 	inner join public.usuario u on u.idempresa = emp.id     "
				+ "     inner join public.Dispositivo disp on disp.idUsuario = u.id "
				+ "	where                                                "
				+ "		disp.token = ? and u.ativo = true	", Tenant.class);
    	query.setParameter(1, token);
    	List<Tenant> lista = query.getResultList();
    	if (!lista.isEmpty()){
    		return lista.get(0);
    	}
    	return null;    	
	}
	
	public Tenant getTenantPorSchemaName(String schemaName)throws Exception {
		Query query = entity.createQuery("Select t from Tenant t "
				+ "	where                                                "
				+ "		t.schemaName = :schemaName", Tenant.class);
    	query.setParameter("schemaName", schemaName);
    	List<Tenant> lista = query.getResultList();
    	if (!lista.isEmpty()){
    		return lista.get(0);
    	}
    	return null;    	
	}

}
