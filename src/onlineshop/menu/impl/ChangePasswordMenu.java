package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;

public class ChangePasswordMenu implements Menu {
	
	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var sc = new Scanner(System.in);
		String userInput = sc.next();
		context.getLoggedInUser().setPassword(userInput);
		System.out.println(context.getString("change.password.msg"));
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("change.password.header"));
		System.out.print(context.getString("enter.new.pass.cta"));		
	}

}
