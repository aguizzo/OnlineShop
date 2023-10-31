package onlineshop.utils.mail;

public interface MailSender {
	
	void sendEmail(String sendTo, String messageToSend);
	
}
