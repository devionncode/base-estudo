package br.com.devionn.das;

import java.io.File;
import java.util.Map;

import javax.ejb.Local;
import javax.mail.Multipart;

import br.com.devionn.model.IContaEmail;
import br.com.devionn.model.Mail;

@Local
public interface EmailBeanLocal {

	public void sendEmail(IContaEmail conta, String[] to, String titulo, String mensagem, String typeMensagem) throws Exception;
	
	public void sendEmail(IContaEmail conta, String[] to, String titulo, String mensagem, String typeMensagem, File[] files) throws Exception;
	
	public void sendEmail(IContaEmail conta, Mail mail, Map<String, byte[]> map) throws Exception;

	public void sendEmail(IContaEmail conta, Mail mail) throws Exception ;
	
	public Multipart gerarAnexo(File[] files) throws Exception;
	
	public Multipart gerarAnexo(Map<String, byte[]> anexos) throws Exception;
	
}
