package br.com.devionn.das;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.com.devionn.model.Direito;
import br.com.devionn.model.DireitoGrupo;
import br.com.devionn.model.GrupoUsuario;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.MenuAcesso;
import br.com.devionn.result.PermissaoAcesso;
import br.com.devionn.result.TelaAcesso;

/**
 * Session Bean implementation class DireitoGrupo
 */
@Stateless
//priva
@LocalBean
public class DireitoGrupoBean extends AbstractBeanImpl<DireitoGrupo> implements DireitoGrupoBeanLocal {

	@EJB
	private MenuBeanLocal menuBean;
	@EJB
	private TelaBeanLocal telaBean;
	@EJB
	private AcaoBeanLocal acaoBean;
	@EJB
	private FiltroBeanLocal filtroBean;
	@EJB
	private ColunaBeanLocal colunaBean;
	@EJB
	private UsuarioBeanLocal usuarioBean;
	@EJB
	private GrupoUsuarioBeanLocal grupoUsuarioBean;
	
    public DireitoGrupoBean() { 
    }

	@Override
	public Class getClasse() {
		return DireitoGrupo.class;
	}
	
	public List<DireitoGrupo> save(Usuario usuario,List<DireitoGrupo> lista) throws Exception {
		for (DireitoGrupo direito : lista){
			if (direito.isNew()){
				insert(usuario,direito);
			}else {
				update(usuario, direito);
			}
		}
		return lista;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean salvarDireitos(Usuario usuario, PermissaoAcesso permissao)throws Exception {
		DireitoGrupo direitoGrupo = null;
		
		for (Direito dir : permissao.getDireitos()){
			direitoGrupo = find(usuario, dir.getId());
			direitoGrupo.setStatus(dir.getStatus());
			save(usuario, direitoGrupo);
		}
		Usuario userBd = null;
		for (Usuario user : permissao.getUsuarios()){
			userBd = usuarioBean.find(usuario, user.getId());
			userBd.setIdGrupoUsuario(user.getIdGrupoUsuario());
			usuarioBean.save(usuario, userBd);
		}
		entity.getEntityManagerFactory().getCache().evict(DireitoGrupo.class);
		entity.getEntityManagerFactory().getCache().evict(DireitoGrupo.class);
		return true;
	}
	
	public List<DireitoGrupo> getByIdGrupoUsuario(Usuario usuario, Long idGrupoUsuario) throws Exception{
		Query query = entity.createQuery("Select d From DireitoGrupo d where d.grupousuario.id = :idGrupoUsuario ");
		query.setParameter("idGrupoUsuario", idGrupoUsuario);
		return query.getResultList();
	}
	
	public List<Direito> getByIdGrupoUsuarioIdTela(Usuario usuario, Long idGrupoUsuario, Long idTela) throws Exception{
		String sql = "Select                                                  "
				+ "		dg.id, dg.idtela,dg.status, dg.idacao,a.nome,a.icone, a.descricao, a.action    "
				+ " from                            	"
				+ "		public.telaacao ta              "
				+ "		inner join public.tela t on t.id = ta.idtela                      "
				+ "		inner join public.acao a on a.id = ta.idacao                              "
				+ "		inner join public.direitogrupo dg on dg.idtela = t.id and dg.idacao = a.id and dg.deletado = false   "
				+ " where                   "
				+ "		a.deletado = false and ta.deletado = false and t.id = ? and dg.idgrupousuario = ?             "
				+ " order by ta.posicao asc ";
		
		Query query = entity.createNativeQuery(sql, Direito.class);
		query.setParameter(1, idTela);
		query.setParameter(2, idGrupoUsuario);
		return query.getResultList();
	}
	
	public List<Direito> findDireitoByIdGrupoUsuario(Usuario usuario, Long idGrupoUsuario,Integer sistema) throws Exception{
		String sql = "Select dg.id, dg.idtela,dg.status, dg.idacao, a.nome, "
				+ "		case when ? = 1 then"
				+ "          a.icone           "
				+ "       else                         "
				+ "          a.iconeAplicativo         "
				+ "     end as icone, a.descricao, a.action "
				+ "	from public.direitogrupo dg   " 
				+ "		inner join public.telaacao ta on dg.idtela = ta.idtela and dg.idacao = ta.idacao     "
				+ "     inner join public.tela t on t.id = ta.idtela  "
				+ "		inner join public.acao a on dg.idacao = a.id                 "
				+ " where  				"
				+ "		dg.status = true and a.deletado = false and ta.deletado = false and dg.deletado = false   "
				+ "		and dg.idgrupousuario = ?	and (t.sistema = ? or t.sistema = 3)                      "
				+ "	order by ta.posicao asc       ";
		
		Query query = entity.createNativeQuery(sql, Direito.class);
		query.setParameter(1, sistema);
		query.setParameter(2, idGrupoUsuario);
		query.setParameter(3, sistema);
		return query.getResultList();
	}
	
	public void gerarDireitos(Usuario usuario)throws Exception {
		List<GrupoUsuario> grupos = grupoUsuarioBean.findAll(usuario);
		for (GrupoUsuario grupo :  grupos){
			gerarDireitos(usuario, grupo);	
		}
	}
	
	
	public void gerarDireitos(Usuario usuario, GrupoUsuario grupo) throws Exception {
		Query query =  entity.createNativeQuery("select public.gerardireitoacesso(?); ");
		query.setParameter(1, grupo.getId());
		List<Object> objs = query.getResultList();
	}
	
	public Map<String,Object> getAmbienteByUsuario(Usuario usuario) throws Exception{
		
		Map<String, Object> map = new HashMap<>();
		map.put("direitos", findDireitoByIdGrupoUsuario(usuario, usuario.getIdGrupoUsuario(), 2));
		
		List<MenuAcesso> menus = menuBean.findMenusPorAcesso(usuario,2);
		map.put("menus", menus);
		List<TelaAcesso> telas = telaBean.findTelasPorAcesso(usuario,2);
		map.put("telas",telas);
		map.put("acoes", acaoBean.findByDireito(usuario,2));
		map.put("filtros", filtroBean.findBySistema(usuario, 2));
		map.put("colunas", colunaBean.findBySistema(usuario, 2));
		return map;
	}
	
	
	
	public List<Direito> findByIdTela(Usuario usuario, Long idTela) throws Exception{
		String sql = "Select dg.id, dg.idtela,dg.status, dg.idgrupousuario, dg.idacao,a.nome,a.icone, a.descricao, a.action "
				+ "	from public.direitogrupo dg   " 
				+ "		inner join public.telaacao ta on dg.idtela = ta.idtela and dg.idacao = ta.idacao     "
				+ "     inner join public.tela t on t.id = ta.idtela  "
				+ "		inner join public.acao a on dg.idacao = a.id                 "
				+ " where  				"
				+ "		dg.status = true and a.deletado = false and ta.deletado = false and dg.deletado = false   "
				+ "		and dg.idtela = ? and dg.idgrupousuario = ?	and (t.sistema = 1 or t.sistema = 3)                      "
				+ "	order by ta.posicao asc       ";
		
		Query query = entity.createNativeQuery(sql, Direito.class);
		query.setParameter(1, idTela);
		query.setParameter(2, usuario.getIdGrupoUsuario());
		return query.getResultList();
	}
	
	@Override
	public Boolean insertNativo(DireitoGrupo obj) throws Exception {
		Query query = entity.createNativeQuery("Insert into public.DireitoGrupo (id,dataalteracao,datacadastro,deletado, versao,status, idacao, idgrupousuario, idtela) "
				+ "  values (?, now(),now(),false , 0, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getStatus());
    	query.setParameter(3, obj.getAcao().getId());
    	query.setParameter(4, obj.getGrupoUsuario().getId());
    	query.setParameter(5, obj.getTela().getId());
    	query.executeUpdate();
		return true;
	}
	
}
