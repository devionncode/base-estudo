package br.com.devionn.model.exceptions;

public class RegraException extends IllegalArgumentException {

	private Integer codErro;
	
	public RegraException() {
		
	}
	
	public RegraException(Integer codErro, String msg) {
		super(msg);
		this.codErro = codErro;
	}
	
	public RegraException(Integer codErro,String msg,Throwable throwable) {
		super(msg,throwable);
		this.codErro = codErro;
	}
	
	public Integer getCodErro() {
		return codErro;
	}
}
