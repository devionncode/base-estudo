package br.com.devionn.das;
import java.io.File;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.LoggerFactory;

import br.com.devionn.model.IContaEmail;
import br.com.devionn.model.Mail;
 
@Stateless
public class EmailBean implements EmailBeanLocal, EmailBeanRemote {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmailBean.class);
	
	@Resource(lookup = "java:jboss/devionn") 
	private Session session; 
	 
	public void sendEmail(IContaEmail conta, String[] to, String titulo, String mensagem, String typeMensagem) throws Exception {
		Message msg = new MimeMessage(session);

		
		for (int w = 0; w < to.length; w++) {
			if (!to[w].isEmpty()) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[w]));
			}
		}
		// Setando a origem do email
		msg.setFrom(new InternetAddress(conta.getEmail()));
		// Setando o assunto
		msg.setSubject(titulo); 
		msg.setContent(mensagem, typeMensagem);
 
		Transport tr;

		tr = session.getTransport(conta.getProtocolo());  
		
		tr.connect(conta.getHost(), conta.getPorta(), conta.getEmail(), conta.getSenha());
		msg.saveChanges(); 
		
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}

	public void sendEmail(IContaEmail conta, String[] to, String titulo, String mensagem, String typeMensagem, File[] files) throws Exception {
		session.setDebug(true); 

		Message msg = new MimeMessage(session);

		for (int w = 0; w < to.length; w++) {
			if (!to[w].isEmpty()) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[w]));
			}
		}
		msg.setFrom(new InternetAddress(conta.getEmail()));
		
		msg.setSubject(titulo);

		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setContent(mensagem, typeMensagem);
		// cria a segunda parte da mensage
		Multipart mp = gerarAnexo(files);
		mp.addBodyPart(mbp1);
		// adiciona a Multipart na mensagem
		// Setando o conteúdo/corpo do email
		msg.setContent(mp);
		enviarMessage(conta, msg);
	}
	
	private void enviarMessage(IContaEmail conta, Message msg)throws Exception {
		Transport tr;
		tr = session.getTransport(conta.getProtocolo());  
		
		tr.connect(conta.getHost(), conta.getPorta(), conta.getEmail(), conta.getSenha());
		msg.saveChanges(); // don't forget this
		// envio da mensagem
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
		
	}

	public void sendEmail(IContaEmail conta, Mail mail, Map<String, byte[]> map) throws Exception {		
		session.setDebug(true); 
		
		Message msg = new MimeMessage(session);
		// Setando o destinatário
		String[] destinatarios = mail.getDestinatarios();
		
		for (int w = 0; w < destinatarios.length; w++) {
			if (!destinatarios[w].isEmpty()) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios[w]));
			}
		}
		// Setando a origem do email
		InternetAddress from = new InternetAddress(conta.getEmail());
		
		msg.setFrom(from);
		// Setando o titulo
		msg.setSubject(mail.getAssunto());
		if (map != null){		
			 
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(mail.getConteudo(), mail.getTypeMensagem());
			// cria a segunda parte da mensage
			Multipart mp = gerarAnexo(map);
			mp.addBodyPart(mbp1);
			
			msg.setContent(mp);
		}
		
		enviarMessage(conta, msg);
	}
	
	public void sendEmail(IContaEmail conta, Mail mail) throws Exception {
		session.setDebug(true); 
		Message msg = new MimeMessage(session);

		for (int w = 0; w < mail.getDestinatarios().length; w++) {
			if (!mail.getDestinatarios()[w].isEmpty()) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatarios()[w]));
			}
		}
		
		msg.setFrom(new InternetAddress(conta.getEmail()));
		
		msg.setSubject(mail.getAssunto());
		
		msg.setContent(mail.getConteudo(), mail.getTypeMensagem());

		enviarMessage(conta, msg);
	}
/**
<outbound-socket-binding name="mail-smtp">
			<remote-destination host="smtp.gmail.com" port="465" />
		</outbound-socket-binding>
 */
	
//	private static class DefaultTrustManager implements X509TrustManager {
//
//		@Override
//		public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
//		}
//
//		@Override
//		public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
//		}
//
//		@Override
//		public X509Certificate[] getAcceptedIssuers() {
//			return null;
//		}
//	}

	public Multipart gerarAnexo(File[] files) throws Exception {
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp2 = null;// new MimeBodyPart();
		for (int w = 0; w < files.length; w++) {
			mbp2 = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(files[w]);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());
			mp.addBodyPart(mbp2);
		}
		return mp;
	}
	
	public Multipart gerarAnexo(Map<String, byte[]> anexos) throws Exception {
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp2 = null;
		for (String nome : anexos.keySet()) {
			mbp2 = new MimeBodyPart();
			ByteArrayDataSource fds = new ByteArrayDataSource(anexos.get(nome), "application/octet-stream");
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(nome);
			mp.addBodyPart(mbp2);
		}
		return mp;
	}
	
}
