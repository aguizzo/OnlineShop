package onlineshop.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;

import onlineshop.Main;
import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;

public class MainMenu implements Menu {
	
	public static final String MENU_COMMAND = "menu";
	
	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {

		if (context.getMainMenu() == null) {
			context.setMainMenu(this);
		}

		Menu menuToNavigate = null;

		while (true) {
			printMenuHeader();
			var sc = new Scanner(System.in);
			System.out.print(context.getString("user.input"));
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
				case 7:
					menuToNavigate = new ResetPasswordMenu();
					break; 
				case 8:
					menuToNavigate = new ChangeLanguageMenu();
					break;
				default:
					System.out.println(context.getString("err.msg"));
					continue;
				}
				menuToNavigate.start();
			} 
			else
				System.out.println(context.getString("err.msg"));
		}

//		System.out.println("Bye!");
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
		if (context.getLoggedInUser() == null) 
			System.out.println(context.getString("menu.for.not.logged.in.user"));
		else 
			System.out.println(context.getString("menu.for.logged.in.user"));
	}

}
