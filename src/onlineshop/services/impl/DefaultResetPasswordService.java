package onlineshop.services.impl;

import onlineshop.enteties.User;
import onlineshop.services.ResetPasswordService;
import onlineshop.utils.mail.MailSender;

public class DefaultResetPasswordService implements ResetPasswordService {
	
	private MailSender mailSender;
	
	@Override
	public void resetPasswordForUser(User user) {
		System.out.println("Your password was sent to your email. Check your inbox.");
		mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
	}

}
