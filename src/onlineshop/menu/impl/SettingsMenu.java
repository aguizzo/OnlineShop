package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator() + "2. Change Email";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		while (true) {
			printMenuHeader();
			var sc = new Scanner(System.in);
			if (context.getLoggedInUser() != null) {
				System.out.println(SETTINGS);
				System.out.print(
						context.getString("enter.option"));
				var userInput = sc.next();
				
				if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND))
					break;
				// -? --> negative sign, could have none or one
				// \\d+ --> one or more digits
				if (userInput.matches("-?\\d+")) {
					int chosenOption = Integer.parseInt(userInput);
					Menu menu;
					switch (chosenOption) {
					case 1:
						menu = new ChangePasswordMenu();
						menu.start();
						break;
					case 2:
						menu = new ChangeEmailMenu();
						break;
					default:
						System.out.println(context.getString("settings.option.validation.msg"));
						continue;
					}
					menu.start();
				}
				else
					System.out.println(context.getString("settings.option.validation.msg"));;
			}
			else {
				System.out.println("Please, log in or create new account to change your account settings");
				break;
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("settings.menu.header"));
	}

}
