package br.com.devionn.das;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Menu;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.MenuAcesso;

/**
 * Session Bean implementation class MenuBean
 */
@Stateless
@LocalBean
public class MenuBean extends AbstractBeanImpl<Menu> implements MenuBeanLocal {

    /**
     * Default constructor. 
     */
    public MenuBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Menu.class;
	}

	public List<Menu> getByIdModulo(Long idModulo) throws Exception {
		Query query = entity.createQuery("Select m From Menu m where m.modulo.id = :idModulo ");
		query.setParameter("idModulo", idModulo);
		return query.getResultList();
	}
	
	
	public List<MenuAcesso> findMenusPorAcesso(Usuario usuario,Integer sistema) throws Exception {
		String sql = "Select distinct m.id, m.nome, m.posicao, m.ativo, m.expandido, m.icone, m.descricao  from public.Menu m     "
				+ "		inner join public.tela t on m.id = t.idmenu and t.deletado = false and t.ativo = true    "
				+ "		inner join public.direitogrupo dg on dg.idtela = t.id and dg.status = true and dg.deletado = false  "
				+ "	where                    					"
				+ "		dg.idgrupousuario = ? and m.deletado = false and m.ativo = true and ( t.sistema = ? or t.sistema = 3) and t.idtelapai is null "
				+ "order by m.posicao asc ";
		Query query = entity.createNativeQuery(sql, MenuAcesso.class);
		query.setParameter(1, usuario.getIdGrupoUsuario());
		query.setParameter(2, sistema);
		return query.getResultList();
	}
	
	
	@Override
	public Boolean insertNativo(Menu obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into public.Menu (id,dataalteracao,datacadastro,deletado, versao,ativo,descricao, expandido, icone, nome, posicao, idmodulo)  "
				+ "  values (?, now(),now(),false , 0, ?, ?, ?, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getAtivo());
    	query.setParameter(3, obj.getDescricao());
    	query.setParameter(4, obj.getExpandido());
    	query.setParameter(5, obj.getIcone());
    	query.setParameter(6, obj.getNome());
    	query.setParameter(7, obj.getPosicao());
    	query.setParameter(8, obj.getModulo().getId());
    	query.executeUpdate();
		return true;
	}
}
