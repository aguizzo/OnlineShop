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
						"Please, enter option or type 'menu' to navigate back to the main menu: ");
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
						System.out.println("Change password");
						menu = new ChangePasswordMenu();
						menu.start();
						break;
					case 2:
						System.out.println("Change email");
						menu = new ChangeEmailMenu();
						break;
					default:
						System.out.println("Only 1, 2 is allowed. Try one more time");
						continue;
					}
					menu.start();
				}
				else
					System.out.println("Only 1, 2 is allowed. Try one more time");
			}
			else {
				System.out.println("Please, log in or create new account to change your account settings");
				break;
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SETTINGS *****");
	}

}
