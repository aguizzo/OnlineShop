package onlineshop.menu.impl;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import onlineshop.enteties.User;
import onlineshop.menu.Menu;
import onlineshop.services.ResetPasswordService;
import onlineshop.services.UserManagementService;
import onlineshop.services.impl.DefaultResetPasswordService;
import onlineshop.services.impl.DefaultUserManagementService;

public class ResetPasswordMenu implements Menu {
	
	private ResetPasswordService resetPasswordService;
	private UserManagementService userManagementService;
	
	{
		resetPasswordService = new DefaultResetPasswordService();
		userManagementService = DefaultUserManagementService.getInstance();
	}
	

	@Override
	public void start() {
		printMenuHeader();
		var sc = new Scanner(System.in);
		String userInput = sc.next();
		System.out.println("Your password has been sent to your email. Please, check mailbox. "
				+ "You will receive the email within the next 5 minutes");
		CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user = userManagementService.getUserByEmail(userInput);
			resetPasswordService.resetPasswordForUser(user);
		});
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** RESET PASSWORD MENU *****");
		System.out.print("Please, enter your email: ");
	}

}
