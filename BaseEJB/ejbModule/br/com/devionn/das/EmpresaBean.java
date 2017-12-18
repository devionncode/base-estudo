package br.com.devionn.das;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.com.devionn.model.Empresa;
import br.com.devionn.model.GrupoUsuario;
import br.com.devionn.model.Tenant;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.NovoAmbiente;

/**
 * Session Bean implementation class EmpresaBean
 */
@Stateless
public class EmpresaBean extends AbstractBeanImpl<Empresa> implements EmpresaBeanLocal, EmpresaBeanRemote {

	@EJB
    TenantBeanRemote tenantBean;
	@EJB
	private GrupoUsuarioBeanLocal grupoUsuarioBean;
	@EJB
	private UsuarioBeanLocal usuarioBean;
	@EJB
	private DireitoGrupoBeanLocal direitoGrupoBean; 
//	@Inject
//	TenantRe
    /**
     * Default constructor. 
     */
    public EmpresaBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Class getClasse() {
    	return Empresa.class;
    }

    @Override
    public Empresa save(Usuario usuario, Empresa obj) throws Exception {
    	if (obj.getIdTenant() == null){
    		Tenant tenant = new Tenant();
    		tenant.setPassword("");
    		tenant.setSchemaName("sc_"+obj.getCnpj());
    		tenant.setUnitName("SalesModel");
    		tenant = tenantBean.save(usuario, tenant);
    		obj.setIdTenant(tenant.getId());
    	}
    	return super.save(usuario, obj);
    }
    
    public Empresa findPorUsuario(Usuario usuario)throws Exception {
    	Query query = entity.createNativeQuery("Select emp.* From public.Usuario u inner join public.empresa emp on u.idempresa = emp.id where u.id = ? ", Empresa.class);
    	query.setParameter(1, usuario.getId());
    	List<Empresa> empresas = query.getResultList();
    	if ( empresas.isEmpty()){
    		return null;
    	}
    	return empresas.get(0);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean novoAmbiente(Usuario usuario, NovoAmbiente novoAmbiente) throws Exception {
    	GrupoUsuario grupo = criarAmbiente(usuario, novoAmbiente);
    	liberarAcessoAoAmbiente(usuario, grupo);
    	return true;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public GrupoUsuario criarAmbiente(Usuario usuario,NovoAmbiente novoAmbiente) throws Exception {
    	Empresa empresa = novoAmbiente.getEmpresa();
    	Tenant tenant = new Tenant();
		tenant.setPassword("");
		tenant.setSchemaName("sc_"+empresa.getCnpj());
		tenant.setUnitName("SalesModel");
		tenant = tenantBean.save(usuario, tenant);
		empresa.setIdTenant(tenant.getId());
    	empresa = super.save(usuario, empresa);
    	
    	tenantBean.createSchema(tenant);
    	
    	GrupoUsuario grupo = new GrupoUsuario();
    	grupo.setAtivo(true);
    	grupo.setNome("Adminstrador");
    	grupo.setIdEmpresa(empresa.getId());
    	grupo.setDataAlteracao(new Date());
    	grupo.setDataCadastro(new Date());
    	grupo.setDeletado(false);
    	entity.persist(grupo);
    	entity.flush();
    	
    	Usuario user = novoAmbiente.getUsuario();
    	user.setGrupoUsuario(grupo);
    	user.setIdEmpresa(empresa.getId());
    	user.setDataAlteracao(new Date());
    	user.setDataCadastro(new Date());
    	user.setDeletado(false);
    	entity.persist(user);
    	entity.flush();
    	
    	return grupo;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void liberarAcessoAoAmbiente(Usuario usuario, GrupoUsuario grupo) throws Exception {
    	direitoGrupoBean.gerarDireitos(usuario, grupo);
    	
    	Query query = entity.createNativeQuery("update public.direitoGrupo set status = true where idGrupoUsuario = ? ");
    	query.setParameter(1, grupo.getId());
    	query.executeUpdate();
    }
    
}
