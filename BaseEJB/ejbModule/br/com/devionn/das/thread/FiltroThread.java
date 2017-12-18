package br.com.devionn.das.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.Session;

import com.google.gson.Gson;

import br.com.devionn.das.FiltroBean;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.Filter;
import br.com.devionn.result.Variavel;

public class FiltroThread extends Thread {
	
	final String ULTIMA_BUSCA = "ultimaBusca";
	
	private FiltroBean bean;
	
	private Usuario usuario;
	private Filter filter;
	private Integer intervalo;
	private List<Session> sessions;
	
	private Gson gson; 
	
	public void addSession(Session session) {
		if (this.sessions == null) {
			this.sessions = new ArrayList<>();
		}
		this.sessions.add(session);
	}

	public FiltroThread(Usuario usuario, Filter filter) {
		this.usuario = usuario;
		this.filter = filter;
		this.intervalo = filter.getIntervalo() * 1000;
		
		this.gson = new Gson();
		
		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			this.bean = (FiltroBean) initialContext.lookup("java:module/FiltroBean!br.com.devionn.das.FiltroBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		String ultimaBusca = "'1900-01-01 00:00'";
		filter.getVariaveis().add(new Variavel(ULTIMA_BUSCA, ultimaBusca));
		
		while(!this.isInterrupted()) {
			
			try {
				ultimaBusca = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "'";
				
				Filter retorno = bean.getByFilter(usuario, filter);
				
				for (Variavel var : filter.getVariaveis()) {
					if (var.getNome().equals(ULTIMA_BUSCA)) {
						var.setValor(ultimaBusca);
					}
				}
				
				if (retorno.getData() != null) {
					notificarClientes(retorno);
				}
				
				Thread.sleep(intervalo);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void notificarClientes(Filter retorno) throws Exception {
		for (Session session : this.sessions) {
			session.getBasicRemote().sendText(this.gson.toJson(retorno));
		}
	}

	public Filter getFilter() {
		return filter;
	}
	
	public List<Session> getSessions() {
		return sessions;
	}
}
