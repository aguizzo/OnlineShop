package services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import onlineshop.enteties.User;
import onlineshop.enteties.impl.DefaultUser;
import onlineshop.services.ResetPasswordService;
import onlineshop.services.impl.DefaultResetPasswordService;
import onlineshop.utils.mail.MailSender;

@ExtendWith(MockitoExtension.class)
class ResetPasswordServiceTest {
	
	@InjectMocks
	private ResetPasswordService resetPasswordService = new DefaultResetPasswordService(); 
	
	@Mock // MailSender is not implement yet, so we use a Mock
	private MailSender mailSenderMock;
	
	@Captor
	private ArgumentCaptor<String> captor;

	@Test
	void shouldSendPasswordToUserEmail() {
		User user = new DefaultUser();
		String userEmail = "testemail@email.com";
		user.setEmail(userEmail);
		
		resetPasswordService.resetPasswordForUser(user);
		
		verify(mailSenderMock).sendEmail(captor.capture(), anyString());
		assertEquals(captor.getValue(), userEmail);
	}
	
	@Test
	void shouldSendPasswordInfo() {
		User user = new DefaultUser();
		String userPassword = "password";
		user.setPassword(userPassword);
		
		resetPasswordService.resetPasswordForUser(user);
		
		verify(mailSenderMock).sendEmail(any(), captor.capture());
		assertTrue(captor.getValue().split(":")[1].trim().equals(userPassword));
	}

}
