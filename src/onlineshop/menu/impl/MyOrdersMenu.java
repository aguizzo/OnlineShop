package onlineshop.menu.impl;


import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.Purchase;
import onlineshop.menu.Menu;
import onlineshop.services.PurchaseManagementService;
import onlineshop.services.impl.MySqlPurchaseManagementService;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private PurchaseManagementService purchaseManagementService;
	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = MySqlPurchaseManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var loggedUser = context.getLoggedInUser();
		if (loggedUser != null) {
			int loggedUserId = loggedUser.getId();
			var orders = purchaseManagementService.getPurchasesByUserId(loggedUserId);
			if (orders != null && orders.size() > 0)
				for (Purchase order : orders) 
					System.out.println(order);
			else
				System.out.println("Unfortunately, you don't have any orders yet. Navigate back to main menu to place a new order");
		}
		else {
			System.out.println("Please, log in or create new account to see list of your orders");
		}
		
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");
	}

}
