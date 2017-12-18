package br.com.devionn.das;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import br.com.devionn.model.Filtro;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.Clausula;
import br.com.devionn.result.Filter;
import br.com.devionn.result.FiltroResult;
import br.com.devionn.result.RetornoFiltro;
import br.com.devionn.result.Variavel;

/**
 * Session Bean implementation class FiltroBean
 */
@Stateless
@LocalBean
public class FiltroBean extends AbstractBeanImpl<Filtro> implements FiltroBeanLocal {

 
	@EJB
	private ColunaBeanLocal colunaBean;
	
    public FiltroBean() {
    }

	@Override
	public Class getClasse() {
		return Filtro.class;
	}
	
	public Filtro getConfiguracao(Usuario usuario, Long idFiltro) throws Exception {
		Filtro filtro = find(usuario, idFiltro);
//		if (filtro != null){
//			List<Coluna> colunas = colunaBean.findByIdFiltro(idFiltro);
//			filtro.setColunas(colunas);
//		}
		return filtro;
	}
	
	
	public Filtro getConfiguracaoPorTela(Usuario usuario, Long idTela) throws Exception {
		Query query = entity.createQuery("Select f From Filtro f inner join f.tela t where t.id = :idTela ");
		query.setParameter("idTela", idTela);
		List<Filtro> lista = query.getResultList();
		if (lista.isEmpty()){
			return null;
		}
		return lista.get(0);
	}
	
	
	
	public RetornoFiltro gerarColunas(Usuario usuario,Filtro filtro)throws Exception {
		final String schema = getSchema(usuario);
		Session hibernateSession = entity.unwrap(Session.class);
		RetornoFiltro retorno = new RetornoFiltro();
		
		Work work = new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				try{
					
					List<Map<String,Object>> lista = new ArrayList<>(); 
					Statement stmt = conn.createStatement();
					stmt.execute("set schema '"+ schema + "' ");
					close(stmt);
					
					PreparedStatement pstmt = conn.prepareStatement(filtro.getSql());
					pstmt.setMaxRows(10);
					ResultSet res = pstmt.executeQuery();
					ResultSetMetaData metaData = res.getMetaData();					
					
					int columnCount = metaData.getColumnCount();
					for (int w = 1; w <= columnCount; w++) {
						Map<String, Object> map = new HashMap<String, Object>();
						Class classe = Class.forName(metaData.getColumnClassName(w));
						map.put("propriedade", metaData.getColumnName(w));
						map.put("titulo", metaData.getColumnLabel(w));
						map.put("largura", metaData.getColumnDisplaySize(w));
						map.put("tipo", classe.getSimpleName());
						map.put("variavel", metaData.getColumnName(w));
						map.put("posicao", w);
						if (classe.getSimpleName().equalsIgnoreCase("LONG") || classe.getSimpleName().equalsIgnoreCase("DOUBLE")|| classe.getSimpleName().equalsIgnoreCase("INTEGER")){
							map.put("alinhamento", "DIREITA");
						}else if (classe.getSimpleName().equalsIgnoreCase("STRING") || classe.getSimpleName().equalsIgnoreCase("DATE") || classe.getSimpleName().equalsIgnoreCase("TIMESTAMP")){
							map.put("alinhamento", "ESQUERDA");
						}else {
							map.put("alinhamento", "CENTRO");
						}
						
						lista.add(map);
					}	
					retorno.setColunas(lista);
					retorno.setSucesso(true);
					retorno.setDados(mountMaps(res));
				}catch (Exception e) {
					e.printStackTrace();
					retorno.setSucesso(false);
					retorno.setMensagem(e.getLocalizedMessage());
				} 
			}
		};
		hibernateSession.doWork(work);
		return retorno;
	}
	
	String montarSql(Usuario usuario,String sql, Filtro filtro, Filter filter) throws Exception {
		return montarSql(usuario,sql, filtro, filter, true);
	}
	
	
	String montarSql(Usuario usuario, String sql, Filtro filtro,Filter filter, Boolean createOrderBy) throws Exception {
		
		String groupBy = "";
		String orderBy = "";
		String where = "  ";
		int indexWhere = -1;
		int indexOrder = -1;
		int indexGroup = -1;
		int indexUltimoParentes = sql.lastIndexOf(")");
		if (createOrderBy) {
			/**
			 * Valida��o para o ORDER BY
			 */
			indexOrder = sql.toUpperCase().lastIndexOf("ORDER BY");

			if (indexOrder > -1) {
				String script = sql.substring(0, indexOrder);
				orderBy = sql.substring(indexOrder);
				sql = script;
			}
//			if (requisicao.getSorters() != null 
//					&& !requisicao.getSorters().isEmpty()) {
//				for (int w = 0; w < requisicao.getSorters().size(); w++) {
//					Sort sort = requisicao.getSorters().get(w);
//					if (orderBy.toUpperCase().trim().isEmpty()) {
//						orderBy = " ORDER BY " + sort.getSort();
//					} else {
//						orderBy += ", " + sort.getSort();
//					}
//				}
//			}
		}
		if (filter.getVariaveis() != null && !filter.getVariaveis().isEmpty()){
			for (Variavel vari : filter.getVariaveis()){
				sql = sql.replaceAll("<<"+ vari.getNome()+">>", vari.getValor());
			}
		}	
		if (usuario != null){
			sql = sql.replaceAll("<<idUsuario>>", usuario.getId()+"");
		}
		/**
		 * Valida��o para o Group By
		 */
		if (sql.toUpperCase().contains("GROUP BY")) {
			indexGroup = sql.toUpperCase().lastIndexOf("GROUP BY");
			indexWhere = sql.toUpperCase().lastIndexOf("WHERE ");
			if (indexGroup > indexWhere) {
				groupBy = sql.substring(indexGroup);
				String script = sql.substring(0, indexGroup);
				// filtro.setSql(script);
				sql = script;
			}
		}

		/**
		 * Valida��o para o WHERE
		 */
		boolean comClausula = false;
		indexWhere = sql.toUpperCase().lastIndexOf("WHERE ");
		if (indexWhere < 0){
			indexWhere = sql.toUpperCase().lastIndexOf("WHERE\n");
		}
		
		if (indexWhere == -1) {
//			where = " where ";
			comClausula = false;
		} else {
			where = sql.substring(indexWhere);
			String script = sql.substring(0, indexWhere);
			sql = script;
			comClausula = true;
		}

		if (filter.getClausulas() != null 
				&& !filter.getClausulas().isEmpty()) {
			for (int w = 0; w < filter.getClausulas().size(); w++) {
				Clausula clausula = filter.getClausulas().get(w);
				if (!comClausula && w == 0) {
					where += clausula.getWhere();
				} else {
					where += " and " + clausula.getWhere();
				}
			}
		}
		
		// if (filtro.getAtualiza()) {
		// 	if (comClausula) {
		// 		where += " and ";
		// 	} else {
		// 		where = " where ";
		// 	}
		
		// 	where += " d.datacadastro > '" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(filter.getUltimaBusca()) + "' ";
		// }
		
		return sql + " " + where + " " + groupBy + " " + orderBy;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Filter getByFilter(Usuario usuario,Filter filter) throws Exception {
		Filtro filtro = find(usuario, filter.getIdFiltro());
		try { 
			String schema = getSchema(usuario);
			Session hibernateSession = entity.unwrap(Session.class);
			RetornoFiltro retorno = new RetornoFiltro();
			
			Work work = new Work() {
				
				@Override
				public void execute(Connection conn) throws SQLException {
					PreparedStatement pstmt = null;
					ResultSet res = null;
					String sql = "";
					try{ 
						Statement stmt = conn.createStatement();
						stmt.execute("set schema '"+ schema + "' ");
						close(stmt);
						String paginacao = "  offset ? limit ?  ";
						sql = montarSql(usuario, filtro.getSql(), filtro,filter) + paginacao;						
						pstmt = conn.prepareStatement(sql);
						pstmt.setLong(1, filter.getStart());
						pstmt.setLong(2, filter.getLimit());
						res = pstmt.executeQuery();
						filter.setData(mountMaps(res));
						filter.setSucesso(true);
//						retorno.setSucesso(true);
//						f.setDados(mountMaps(res));
					}catch (Exception e) {
						e.printStackTrace();
						filter.setSucesso(false);
						System.out.println(sql);
						filter.setData(e.getLocalizedMessage());
					}finally {
						if (pstmt != null) {
							pstmt.close();
						}
					}
				}
			};
			hibernateSession.doWork(work);			 
			return filter;
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		} finally {
			
		}
	}
	
	public List<Map<String, Object>> mountMaps(ResultSet res) throws Exception {
		ResultSetMetaData metaData = res.getMetaData();
		int columnCount = metaData.getColumnCount();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		while (res.next()) {
			map = mountMap(res);
			list.add(map);
		}
		return list;

	}

	public Map<String, Object> mountMap(ResultSet res) throws Exception {
		ResultSetMetaData metaData = res.getMetaData();
		Map<String, Object> map = new HashMap<String, Object>();
		int columnCount = metaData.getColumnCount();
		for (int w = 1; w <= columnCount; w++) {
			Class classe = Class.forName(metaData.getColumnClassName(w));
			if (classe == java.sql.Date.class) {
				if (res.getObject(w) == null) {
					map.put(metaData.getColumnName(w), null);
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					map.put(metaData.getColumnName(w), sdf.format(res.getTimestamp(w)));
				}
			} else if (classe == Timestamp.class) {
				if (res.getObject(w) == null) {
					map.put(metaData.getColumnName(w), null);
				} else {
					Date times = res.getTimestamp(w);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					map.put(metaData.getColumnName(w), sdf.format(times));
				}
			}else if (classe == Double.class){
				map.put(metaData.getColumnName(w), res.getDouble(w));
			} else {
				map.put(metaData.getColumnName(w), res.getObject(w));
			}
		}
		return map;
	}
	
	public void close(PreparedStatement pstmt) throws Exception {
		if (pstmt != null) {
			pstmt.close();
		}
	}

	public void close(ResultSet res) throws Exception {
		if (res != null) {
			res.close();
		}
	}

	public void close(Statement stmt) throws Exception {
		if (stmt != null) {
			stmt.close();
		}
	}
	
	public List<FiltroResult> findBySistema(Usuario usuario,Integer sistema) throws Exception {
		String sql = "select                                 "
				+ "		f.id,f.nome, f.sql,pageSize,utilizaPaginacao, f.idtela        "
				+ " from                                      "
				+ "		public.filtro f                              "
				+ "		inner join public.tela t on t.id = f.idtela     "
				+ " where                                      "
				+ " 	f.deletado = false and (t.sistema = ? or t.sistema = 3)   ";  
		Query query = entity.createNativeQuery(sql, FiltroResult.class);
		query.setParameter(1, sistema);
		return query.getResultList();
	}

}
