package br.com.devionn.das;

import java.util.List;

import br.com.devionn.model.Empresa;
import br.com.devionn.model.Usuario;
import br.com.devionn.model.generic.BaseEntity;


public interface AbstractBean <T extends BaseEntity> {
    
	public Boolean insertNativo(T obj) throws Exception;
	
	public Boolean executeSQL(String sql)throws Exception;
	
	public Empresa getEmpresaPorToken(String token);
	
	public Usuario getUsuarioPorToken(String token);
	
	public String getCnpjEmpresaPorToken(String token);
	
    public T save(Usuario usuario,T obj)throws Exception;
    
    public T insert(Usuario usuario,T obj)throws Exception;
    
    public T update(Usuario usuario,T obj)throws Exception;
    
    public Boolean remove(Usuario usuario,T obj)throws Exception;
    
    public Boolean remove(Usuario usuario,Long id)throws Exception;
    
    public T find(Usuario usuario,Long codigo)throws Exception;
    
    public List<T> findAll(Usuario usuario)throws Exception;
    
    public List<T> findAll() throws Exception;
    
    public List<T> findRange(Usuario usuario, int init, int max)throws Exception;
    
    public Class getClasse();
}
