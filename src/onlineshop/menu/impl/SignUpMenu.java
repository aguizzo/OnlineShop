package onlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.impl.DefaultUser;
import onlineshop.menu.Menu;
import onlineshop.services.UserManagementService;
import onlineshop.services.impl.DefaultUserManagementService;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		
		var sc = new Scanner(System.in);

		System.out.print(context.getString("enter.your.first.name"));
		var firstName = sc.next();
		System.out.print(context.getString("enter.your.last.name"));
		var lastName = sc.next();
		System.out.print(context.getString("enter.your.pass"));
		var password = sc.next();
		System.out.print(context.getString("enter.your.email"));
		var email = sc.next();
		
		userManagementService.getUsers(); // needed to assign the proper ID
		
		var user = new DefaultUser(firstName, lastName, password, email);
		
		
		String errorMessage = userManagementService.registerUser(user);
		if (errorMessage == null || errorMessage.isEmpty()) {
			context.setLoggedInUser(user);
			System.out.println(context.getString("user.created.msg"));
		} else {
			System.out.println(errorMessage);
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("sign.up.header"));
	}

}
