package br.com.devionn.model;

public interface IContaEmail {

	public String getProtocolo();
	
	public Boolean getTlsAtivo();
	
	public Boolean getAutenticar() ;	
	
	public String getEmail();
	
	public String getSenha();
	
	public Integer getPorta();
	
	public String getHost();
}
