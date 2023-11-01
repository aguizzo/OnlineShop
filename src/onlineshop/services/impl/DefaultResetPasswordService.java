package onlineshop.services.impl;

import onlineshop.enteties.User;
import onlineshop.services.ResetPasswordService;
import onlineshop.utils.mail.MailSender;

public class DefaultResetPasswordService implements ResetPasswordService {
	
	private MailSender mailSender;
	
	public DefaultResetPasswordService() {
		mailSender = DefaultMailSender.getInstance();
	}
	
	@Override
	public void resetPasswordForUser(User user) {
		mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
	}

}
