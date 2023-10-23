package onlineshop.menu.impl;

import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.User;
import onlineshop.menu.Menu;
import onlineshop.services.UserManagementService;
import onlineshop.services.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {

	@SuppressWarnings("unused")
	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		User[] users = userManagementService.getUsers();
		
		if (users.length == 0) 
			System.out.println("Unfortunately, there are no customers.");
		else 
			for (User user : users) 
				System.out.println(user);
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** USERS *****");	
	}

}
