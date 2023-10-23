package onlineshop;

import onlineshop.menu.Menu;
import onlineshop.menu.impl.MainMenu;

public class Main {
	
	public static final String EXIT_COMMAND = "exit";
	
	public static void main(String[] args) {
		Menu mainMenu = new MainMenu();
		mainMenu.start();
	}

}
