package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.menu.Menu;
import onlineshop.services.OrderManagementService;
import onlineshop.services.impl.DefaultOrderManagementService;
import onlineshop.enteties.impl.DefaultOrder;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		while (true) {
			var sc = new Scanner(System.in);
			printMenuHeader();
			var userInput = sc.next();

			if (addOrder(userInput)) {
				System.out.println(
						"Thanks a lot for your purchase. Details about order delivery are sent to your email.");
				break;
			} 
//			else // TODO 9999: delete output message
//					// https://github.com/AndriiPiatakha/learnit_java_core/blob/master/src/com/itbulls/learnit/javacore/oop/exam/onlineshop/menu/impl/CheckoutMenu.java
//				System.out.println("You've entered invalid credit card number. "
//						+ "Valid credit card should contain 16 digits. " + "Please, try one more time.");
		}
	}

	private boolean addOrder(String creditCard) {
		var order = new DefaultOrder();
		if (!order.isCreditCardNumberValid(creditCard)) {
			return false;
		}
		order.setCreditCardNumber(creditCard);
		order.setCustomerId(context.getLoggedInUser().getId());
		order.setProducts(context.getSessionCart().getProducts());
		
		orderManagementService.addOrder(order);
		context.getSessionCart().clear();
		return true;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** CHECKOUT *****");
		System.out.print("Enter your credit card number without spaces and press enter if you confirm purchase: ");
	}

}
