package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;
import onlineshop.services.UserManagementService;
import onlineshop.services.impl.DefaultUserManagementService;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);

		System.out.print(context.getString("please.enter.email"));
		String userEmail = sc.next();

		System.out.print(context.getString("please.enter.pass"));
		String userPassword = sc.next();
		
		var user = userManagementService.getUserByEmail(userEmail);
		if (user != null && userPassword.equals(user.getPassword())) {
			System.out.printf(context.getString("glad.to.see.you.back"), user.getFirstName(),
					user.getLastName() + System.lineSeparator());
			context.setLoggedInUser(user);
		}
		else
			System.out.println(context.getString("login.and.password.not.exist"));
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("sign.in.header"));	
	}

}
