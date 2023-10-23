package onlineshop.menu.impl;


import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.Order;
import onlineshop.menu.Menu;
import onlineshop.services.OrderManagementService;
import onlineshop.services.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;
	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var loggedUser = context.getLoggedInUser();
		if (loggedUser != null) {
			int loggedUserId = loggedUser.getId();
			var orders = orderManagementService.getOrdersByUserId(loggedUserId);
			if (orders.size() > 0)
				for (Order order : orders) 
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
