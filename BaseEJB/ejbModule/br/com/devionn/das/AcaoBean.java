package br.com.devionn.das;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Acao;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.AcaoAcesso;

/**
 * Session Bean implementation class AcaoBean
 */
@Stateless
@LocalBean
public class AcaoBean extends AbstractBeanImpl<Acao> implements AcaoBeanLocal {

    /**
     * Default constructor. 
     */
    public AcaoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Acao.class;
	}

	@Override
	public Boolean insertNativo(Acao obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into Acao (id,dataalteracao,datacadastro,deletado, versao,action,descricao,icone,nome )  "
				+ "  values (?, now(),now(),false , 0, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getAction());
    	query.setParameter(3, obj.getDescricao());
    	query.setParameter(4, obj.getIcone());
    	query.setParameter(5, obj.getNome());
    	query.executeUpdate();
    	return true;
	}
	
	
	public List<AcaoAcesso> findByDireito(Usuario usuario, Integer sistema) throws Exception{
		String sql = "Select distinct a.id, a.nome, a.action, a.descricao, a.icone  "
				+ " from public.direitogrupo dg                                   "
				+ "		inner join public.telaacao ta on dg.idtela = ta.idtela and dg.idacao = ta.idacao   "
				+ "     inner join public.tela t on t.id = ta.idtela   "
				+ "		inner join public.acao a on dg.idacao = a.id "
				+ " where   "
				+ "		dg.status = true and a.deletado = false and ta.deletado = false and dg.deletado = false   "
				+ "		and dg.idgrupousuario = ?  and ( t.sistema = ? or t.sistema = 3)  	                     "
				+ " order by a.id     ";
		
		Query query = entity.createNativeQuery(sql, AcaoAcesso.class);
		query.setParameter(1, usuario.getIdGrupoUsuario());
		query.setParameter(2, sistema);
		return query.getResultList();
	}
	 
}
