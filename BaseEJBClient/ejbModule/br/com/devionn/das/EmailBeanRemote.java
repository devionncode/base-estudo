package br.com.devionn.das;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;
import javax.mail.Multipart;

import br.com.devionn.model.IContaEmail;
import br.com.devionn.model.Mail;

@Remote
public interface EmailBeanRemote {

	
	public void sendEmail(IContaEmail conta, String[] to, String titulo, String mensagem, String typeMensagem) throws Exception;
	
	public void sendEmail(IContaEmail conta, String[] to, String titulo, String mensagem, String typeMensagem, File[] files) throws Exception;
	
	public void sendEmail(IContaEmail conta, Mail mail, Map<String, byte[]> map) throws Exception;
	
	public Multipart gerarAnexo(File[] files) throws Exception;
	
	public Multipart gerarAnexo(Map<String, byte[]> anexos) throws Exception;
	
	
}
