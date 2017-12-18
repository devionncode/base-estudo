package br.com.devionn.das;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Tela;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.TelaAcesso;

/**
 * Session Bean implementation class TelaBean
 */
@Stateless
@LocalBean
public class TelaBean extends AbstractBeanImpl<Tela> implements TelaBeanLocal, TelaBeanRemote {

    @EJB
    private DireitoGrupoBeanLocal direitoGrupoBean;
	
	
    public TelaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Tela.class;
	}
	
	@Override
	public Boolean insertNativo(Tela obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into public.Tela (id,dataalteracao,datacadastro,deletado, versao,ativo,descricao,grupo, icone, nome, posicao,url, idmenu)  "
				+ "  values (?, now(),now(),false , 0, ?, ?, ?, ?, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getAtivo());
    	query.setParameter(3, obj.getDescricao());
    	query.setParameter(4, obj.getGrupo());
    	query.setParameter(5, obj.getIcone());
    	query.setParameter(6, obj.getNome());
    	query.setParameter(7, obj.getPosicao());
    	query.setParameter(8, obj.getUrl());
    	query.setParameter(9, obj.getMenu().getId());
    	query.executeUpdate();
    	return true;
	}

	public List<Tela> findTelasPorAcesso(Usuario usuario, Long idMenu) throws Exception {
		String sql = "Select distinct t.* from public.Tela t     "
				+ "		inner join public.direitogrupo dg on dg.idtela = t.id and dg.status = true and dg.deletado = false  "
				+ "	where                    					"
				+ "		t.deletado = false and t.ativo = true and dg.idgrupousuario = ? 	"
				+ "     and t.idmenu = ? and (t.sistema = 1 or t.sistema = 3) and t.idtelapai is null "
				+ "order by t.posicao asc ";
		Query query = entity.createNativeQuery(sql, Tela.class);
		query.setParameter(1, usuario.getIdGrupoUsuario());
		query.setParameter(2, idMenu);
		return query.getResultList();
	}
	
	public List<Tela> findTelasPorMenu(Usuario usuario, Long idMenu) throws Exception {
		String sql = "Select distinct t.* from public.Tela t     "
				+ "	where                    					"
				+ "		t.deletado = false and t.ativo = true "
				+ "     and t.idmenu = ? and t.idtelapai is null "
				+ "order by t.posicao asc ";
		Query query = entity.createNativeQuery(sql, Tela.class);
		query.setParameter(1, idMenu);
		return query.getResultList();
	}
	
	public List<Tela> buscarTelasComAcoesPorMenu(Usuario usuario,Long idGrupoUsuario, Long idMenu) throws Exception {
		List<Tela> telas = findTelasPorMenu(usuario, idMenu);
		for (Tela tela : telas){
			tela.setDireitos(direitoGrupoBean.getByIdGrupoUsuarioIdTela(usuario, idGrupoUsuario, tela.getId()));
		}
		return telas;
	}
	
	
	
	public List<TelaAcesso> findTelasPorAcesso(Usuario usuario,Integer sistema) throws Exception {
		String sql = "Select distinct t.id, t.nome, t.descricao, t.posicao, t.url, t.ativo, t.grupo, t.icone, t.idMenu from public.Tela t     "
				+ "		inner join public.direitogrupo dg on dg.idtela = t.id and dg.status = true and dg.deletado = false  "
				+ "	where                    					"
				+ "		t.deletado = false and t.ativo = true and dg.idgrupousuario = ? "
				+ "     and ( t.sistema = ? or t.sistema = 3 )	"
				+ "order by t.posicao asc ";
		Query query = entity.createNativeQuery(sql, TelaAcesso.class);
		query.setParameter(1, usuario.getIdGrupoUsuario());
		query.setParameter(2, sistema);
		return query.getResultList();
	}
	
	public List<Tela> findPorNome(Usuario usuario, String nome) throws Exception {
		String sql = "Select distinct t from Tela t "
				+ " where upper(t.nome) like :nome    "
				+ "order by t.posicao asc ";
		Query query = entity.createQuery(sql, Tela.class);
		query.setParameter("nome", "%"+ nome.trim().toUpperCase()+"%");
		return query.getResultList();
	}
}
