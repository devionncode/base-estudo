package br.com.devionn.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import br.com.devionn.das.AbstractBean;
import br.com.devionn.das.FiltroBeanLocal;
import br.com.devionn.das.UsuarioBeanRemote;
import br.com.devionn.das.thread.FiltroThread;
import br.com.devionn.model.Filtro;
import br.com.devionn.result.Filter;

@Stateful
@ServerEndpoint(value = "/filtrosocket/{chave}")
public class FiltroSocket extends AbstractWs<Filtro> {
	
	@EJB
	private FiltroBeanLocal filtroBean;
	
	@EJB
	private UsuarioBeanRemote usuarioBean;

	private Map<String, List<FiltroThread>> sessions = new HashMap<>();
	
	@Override
	public AbstractBean<Filtro> getDAS() {
		return filtroBean;
	}
	
	@OnMessage
	public void onMessage(@PathParam("chave") String token, Session session, String message) {
		try {
			Filter filter = null;
			if (message != "") {
				filter = getGson().fromJson(message, Filter.class);
				String cnpj = usuarioBean.getCnpjEmpresaPorToken(token);
				this.addFiltro(cnpj, filter, session, token);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@OnOpen
	public void onOpen(@PathParam("chave") String token, Session session, EndpointConfig endpointConfig) {
		try {
//			String token = session.getPathParameters().get("chave");
//			String filterString = session.getPathParameters().get("filter");
			
			
			String cnpj = usuarioBean.getCnpjEmpresaPorToken(token);
			
			add(cnpj, session);
			System.out.println("Session FiltroWS: Conexão Aberta Cnpj: " + cnpj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		this.removerSession(session);
	}
	
	
	public boolean existeCnpj(String cnpj) {
		return sessions.containsKey(cnpj);
	}
	
	public void add(String cnpj, Session session) {
		if (!existeCnpj(cnpj)) {
			this.sessions.put(cnpj, new ArrayList<FiltroThread>());
		}
//		this.addFiltro(cnpj, filter, session);
		
	}
	
	public void addFiltro(String cnpj, Filter filter, Session session, String token) {
		FiltroThread encontrada = existeThread(cnpj, filter);
		if (encontrada == null) {
			encontrada = new FiltroThread(this.usuarioBean.getUsuarioPorToken(token), filter);
			this.sessions.get(cnpj).add(encontrada);
			encontrada.start();
		}
		encontrada.addSession(session);
		
		if (encontrada.isInterrupted()) {
			encontrada.start();
		}
	}
	
	public FiltroThread existeThread(String cnpj, Filter filter) {
		for (FiltroThread th : sessions.get(cnpj)) {
			if (th.getFilter().getIdFiltro().equals(filter.getIdFiltro())) {
				return th;
			}
		}
		return null;
	}

	public void removerSession(Session session) {
		for (int i = 0; i < this.sessions.values().size(); i++) {
			for (FiltroThread th : this.sessions.values().iterator().next()) {
				th.getSessions().remove(session);
				if (th.getSessions().size() == 0) {
					th.interrupt();
				}
			}
		}
		
	}
}
