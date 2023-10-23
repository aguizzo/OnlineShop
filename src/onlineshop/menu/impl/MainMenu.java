package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.Main;
import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign In" + System.lineSeparator()
			+ "3. Product Catalog" + System.lineSeparator() + "4. My Orders" + System.lineSeparator() + "5. Settings"
			+ System.lineSeparator() + "6. Customer List";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign Out" + System.lineSeparator()
			+ "3. Product Catalog" + System.lineSeparator() + "4. My Orders" + System.lineSeparator() + "5. Settings"
			+ System.lineSeparator() + "6. Customer List";;

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {

		if (context.getMainMenu() == null) {
			context.setMainMenu(this);
		}

		Menu menuToNavigate = null; // Garbage collector do your job!

		while (true) {
			printMenuHeader();
			var sc = new Scanner(System.in);
			System.out.print("User input: ");
			String userInput = sc.next();
			
			if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND))
				break;

			// -? --> negative sign, could have none or one
			// \\d+ --> one or more digits
			if (userInput.matches("-?\\d+")) {
				int chosenOption = Integer.parseInt(userInput);
				switch (chosenOption) {
				// Sign Up
				case 1:
					menuToNavigate = new SignUpMenu();
					break;
				// Sign In or Sign Out
				case 2:
					if (context.getLoggedInUser() == null)
						menuToNavigate = new SignInMenu();
					else
						menuToNavigate = new SignOutMenu();
					break;
				// Product Catalog
				case 3:
					menuToNavigate = new ProductCatalogMenu();
					break;
				// Orders
				case 4:
					menuToNavigate = new MyOrdersMenu();
					break;
				// Settings
				case 5:
					menuToNavigate = new SettingsMenu();
					break;
				// Customer List
				case 6:
					menuToNavigate = new CustomerListMenu();
					break;
				default:
					System.out.println("Only 1, 2, 3, 4, 5 is allowed. Try one more time");
					continue;
				}
				menuToNavigate.start();
			} 
			else
				System.out.println("Only 1, 2, 3, 4, 5 is allowed. Try one more time");
		}

//		System.out.println("Bye!");
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
		if (context.getLoggedInUser() == null) {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		} else {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
		}
	}

}
