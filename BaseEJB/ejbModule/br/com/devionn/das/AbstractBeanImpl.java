/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.das;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import br.com.devionn.model.Empresa;
import br.com.devionn.model.Tenant;
import br.com.devionn.model.Usuario;
import br.com.devionn.model.exceptions.VersionException;
import br.com.devionn.model.generic.BaseEntity;

/**
 *
 * @author anderson
 * @param <T>
 */


public abstract class AbstractBeanImpl<T extends BaseEntity> implements AbstractBean<T>{

//	@Default
    @PersistenceContext(unitName = "BaseEJB",synchronization=SynchronizationType.SYNCHRONIZED)
    protected EntityManager entity; 
    
    public AbstractBeanImpl(){
    }
    
    public Boolean insertNativo(T obj) throws Exception {
    	
    	return false;
    	
    }

    public String getSchema(Usuario usuario)throws Exception {
    	String sql = "select                            "
    			+ "		t.*                    "
    			+ "	from                              "
    			+ "		public.tenant t                    "
    			+ "		inner join public.empresa emp on emp.idtenant = t.id        "
    			+ "		inner join public.usuario u on u.idempresa = emp.id        "
    			+ "	where			"
    			+ "		u.id = ?      ";
    	Query query = entity.createNativeQuery(sql,Tenant.class);
    	query.setParameter(1, usuario.getId());
    	List<Tenant> lista = query.getResultList();
    	if (lista.isEmpty()){
    		return null;
    	}
    	return lista.get(0).getSchemaName();
    }
    
    public Boolean executeSQL(String sql)throws Exception { 
    	Session hibernateSession = entity.unwrap(Session.class);
		
		Work work = new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				Statement pstmt = conn.createStatement();
				
				pstmt.execute(sql);
			}
		};
		hibernateSession.doWork(work);    	
    	return true;
    }
    
    
    public Usuario getUsuarioPorToken(String token){
    	String sql = "select                                                 "
    			+ "		u.login,u.id, u.token,u.idempresa, p.razaoSocial, p.fantasia, p.cnpj, t.schemaName, u.idGrupoUsuario, emp.cnpj as cnpjEmpresa, p.versao, disp.id as idDispositivo "
    			+ " from                                    "
    			+ "		public.usuario u                           "
    			+ "		inner join public.pessoa p on p.id = u.id                     "
    			+ "		inner join public.empresa emp on emp.id = u.idempresa         "
    			+ "		inner join public.tenant t on t.id = emp.idtenant "
    			+ " 	inner join public.dispositivo disp on disp.idusuario = u.id   "
    			+ " where disp.token = ? ";
    	Query query = entity.createNativeQuery(sql,"UsuarioLogado");
    	query.setParameter(1, token);
    	List<Usuario> lista = query.getResultList();
    	if (lista.isEmpty()){
    		return null;
    	}
    	return lista.get(0);
    }
    
    public Empresa getEmpresaPorToken(String token){
    	String sql = "Select emp.* from public.Empresa emp "
    			+ " 	inner join public.usuario u on u.idempresa = emp.id  "
    			+ " 	inner join public.dispositivo disp on disp.idusuario = u.id "
    			+ "	where disp.token = ? "; 
    	Query query = entity.createNativeQuery(sql, Empresa.class);
    	query.setParameter(1, token);
    	List<Empresa> lista = query.getResultList();
    	if (lista.isEmpty()){
    		return null;
    	}
    	return lista.get(0);
    }
    
    
    public String getCnpjEmpresaPorToken(String token){
    	String sql = "Select emp.cnpj from public.Empresa emp "
    			+ "   inner join public.usuario u on u.idempresa = emp.id     "
    			+ "   inner join public.dispositivo disp on disp.idusuario = u.id "
    			+ " where disp.token = ? ";
    	Query query = entity.createNativeQuery(sql);
    	query.setParameter(1, token);
    	List<String> lista = query.getResultList();
    	if (lista.isEmpty()){
    		return null;
    	}
    	return lista.get(0);
    }
    
    @Override
    public T save(Usuario usuario,T obj) throws Exception {
        if (obj.isNew()){
            return insert(usuario,obj);
        }else {
            return update(usuario,obj);
        }        
    }

    @Override
    public T insert(Usuario usuario,T obj) throws Exception {
    	obj.setDataCadastro(new Date());
    	obj.setDataAlteracao(new Date());
    	obj.setDeletado(false);
        entity.persist(obj);
        entity.flush();
        return obj;
    }

    @Override
    public T update(Usuario usuario,T obj) throws Exception {
        T t = (T) entity.getReference(obj.getClass(), obj.getId());
        try {
	        obj.setDataAlteracao(new Date());
	        obj = entity.merge(obj);
	        entity.flush();
        }catch (OptimisticLockException e) {
        	e.printStackTrace();
        	if (t.getDeletado() == false){
        		throw new VersionException("REGISTRO_ATUALIZADO_POR_OUTRO_USUARIO_FAVOR_CONSULTAR_OS_DADOS_NOVAMENTE");
        	}else {
        		throw new VersionException("REGISTRO_FOI_EXCLUIDO_POR_OUTRO_USUARIO");
        	}
		}
        return obj;
    }

    @Override
    public Boolean remove(Usuario usuario,T obj) throws Exception {
        entity.getReference(obj.getClass(), obj.getId());
        obj.setDataAlteracao(new Date());
        obj.setDeletado(true);
        entity.remove(obj);
        entity.flush();
        return true;
    }

    @Override
    public Boolean remove(Usuario usuario,Long id) throws Exception {
        Object obj = entity.getReference(getClasse(), id);
        if (obj instanceof BaseEntity){
        	((BaseEntity)obj).setDataAlteracao(new Date());
        	((BaseEntity)obj).setDeletado(true);
        } 
        entity.remove(obj);
        entity.flush();
        return true;
    }
 
    @Override
    public T find(Usuario usuario,Long codigo) throws Exception { 
        return (T)entity.find(getClasse(), codigo);
    }

    @Override
    public List<T> findAll(Usuario usuario) throws Exception {         
        try { 
            CriteriaQuery cq = entity.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getClasse()));
            Query q = entity.createQuery(cq);            
            return q.getResultList();
        } finally {
        }
    }
    
    @Override
    public List<T> findAll() throws Exception {         
        try { 
            CriteriaQuery cq = entity.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getClasse()));
            Query q = entity.createQuery(cq);            
            return q.getResultList();
        } finally {
        }
    }

    @Override
    public List<T> findRange(Usuario usuario,int init, int max) throws Exception {
        try {
            CriteriaQuery cq = entity.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getClasse()));
            Query q = entity.createQuery(cq);            
            return q.getResultList();
        } finally {
        }
    } 
}
