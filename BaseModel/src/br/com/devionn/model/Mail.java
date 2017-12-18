package br.com.devionn.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Entity;
@Entity
public class Mail implements Serializable  {

	private String[] destinatarios;
	private String assunto;
	private String conteudo;
	private String typeMensagem = "text/plain";//"html/plain
	private byte[] anexos;
	
	public byte[] getAnexos() {
		return anexos;
	}
	
	public void setAnexos(byte[] anexos) {
		this.anexos = anexos;
	}
	
	public String[] getDestinatarios() {
		return destinatarios;
	}


	public String getAssunto() {
		return assunto;
	}


	public void setAssunto(String titulo) {
		this.assunto = titulo;
	}


	public String getConteudo() {
		return conteudo;
	}


	public void setConteudo(String assunto) {
		this.conteudo = assunto;
	}


	public String getTypeMensagem() {
		return typeMensagem;
	}


	public void setTypeMensagem(String typeMensagem) {
		this.typeMensagem = typeMensagem;
	}


	public void setDestinatarios(String[] destinatarios) {
		this.destinatarios = destinatarios;
	}
	
	
}
