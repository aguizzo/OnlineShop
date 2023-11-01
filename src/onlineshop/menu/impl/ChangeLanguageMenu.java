package onlineshop.menu.impl;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;

public class ChangeLanguageMenu implements Menu {

	private static final int ENGLISH_ID = 1;
	private static final int SPANISH_ID = 2;
	private static final int RUSSIAN_ID = 3;
	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);
		int languageId = sc.nextInt();
		switch (languageId) {
		
		case ENGLISH_ID:
			Locale.setDefault(new Locale("en"));
			break;
			
		case SPANISH_ID:
			Locale.setDefault(new Locale("es"));
			break;
		
		case RUSSIAN_ID:
			Locale.setDefault(new Locale("ru"));
			break;
		}
		
		context.changeLanguage();
		
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("change.language.header"));
		System.out.print(context.getString("select.language.cta"));
	}

}
