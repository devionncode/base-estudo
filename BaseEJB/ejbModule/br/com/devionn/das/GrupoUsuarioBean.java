package br.com.devionn.das;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.com.devionn.model.GrupoUsuario;
import br.com.devionn.model.Usuario;

/**
 * Session Bean implementation class GrupoUsuarioBean
 */
@Stateless
@LocalBean
public class GrupoUsuarioBean extends AbstractBeanImpl<GrupoUsuario> implements GrupoUsuarioBeanLocal {
	
	@EJB
	private DireitoGrupoBeanLocal direitoGrupoBean;
   
    public GrupoUsuarioBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		// TODO Auto-generated method stub
		return GrupoUsuario.class;
	}

	@Override
	public GrupoUsuario save(Usuario usuario, GrupoUsuario obj) throws Exception {
		obj.setIdEmpresa(usuario.getIdEmpresa());
		return super.save(usuario, obj);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<GrupoUsuario> findParaDireitosAcesso(Usuario usuario)throws Exception {
		List<GrupoUsuario> grupos = findAll(usuario);
		for (GrupoUsuario grupo : grupos){
			direitoGrupoBean.gerarDireitos(usuario, grupo);
		}
		return grupos;
	}
	
	
	@Override
	public List<GrupoUsuario> findAll(Usuario usuario) throws Exception {
		Query query = entity.createQuery("Select g From GrupoUsuario g where g.idEmpresa = :idEmpresa and g.deletado = false order by g.nome ");
		query.setParameter("idEmpresa", usuario.getIdEmpresa());
		return query.getResultList();
	}
	
	@Override
	public Boolean insertNativo(GrupoUsuario obj) throws Exception {
		Query query = entity.createNativeQuery("Insert into public.GrupoUsuario (id,dataalteracao,datacadastro,deletado, versao,ativo, descricao, nome, idempresa)  "
				+ "  values (?, now(),now(),false , 0, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getAtivo());
    	query.setParameter(3, obj.getDescricao());
    	query.setParameter(4, obj.getNome());
    	query.setParameter(5, obj.getIdEmpresa());
    	query.executeUpdate();
		return true;
	}
}
