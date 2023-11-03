package onlineshop.menu.impl;

import java.util.List;

import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.User;
import onlineshop.menu.Menu;
import onlineshop.services.UserManagementService;
import onlineshop.services.impl.DefaultUserManagementService;
import onlineshop.services.impl.MySqlUserManagementService;

public class CustomerListMenu implements Menu {

	@SuppressWarnings("unused")
	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = MySqlUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		List<User> users = userManagementService.getUsers();
		
		if (users == null || users.size() == 0) 
			System.out.println(context.getString("no.users.msg"));
		else 
			for (User user : users) 
				System.out.println(user);
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("customer.list.header"));	
	}

}
