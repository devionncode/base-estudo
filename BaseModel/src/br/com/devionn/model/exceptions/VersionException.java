package br.com.devionn.model.exceptions;

public class VersionException extends IllegalArgumentException {

	public VersionException() {
		
	}
	
	public VersionException(String msg) {
		super(msg);
	}
	
	public VersionException(String msg,Throwable throwable) {
		super(msg,throwable);
	}
}
