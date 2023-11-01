package onlineshop.menu.impl;

import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;

public class SignOutMenu implements Menu {

	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		context.setLoggedInUser(null);
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("sign.out.header"));
		System.out.println(context.getString("bye.msg"));		
	}

}
