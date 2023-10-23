package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.Product;
import onlineshop.menu.Menu;
import onlineshop.services.ProductManagementService;
import onlineshop.services.impl.DefaultProductManagementService;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {
		while (true) {
			printMenuHeader();
			printProductsToConsole();

			var userInput = readUserInput();

			if (context.getLoggedInUser() == null) {
				System.out.println("You are not logged in. Please, sign in or create new account");
				break;
			}

			if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND))
				break;

			if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				if (context.getSessionCart().isEmpty())
					System.out.println("Your cart is empty. Please, add product to cart first and then proceed with checkout");
				else {
					Menu menu = new CheckoutMenu();
					menu.start();
					break;
				}
			}
			else {
				Product productToAddToCart = fetchProduct(userInput);

				if (productToAddToCart != null)
					processAddToCart(productToAddToCart);
				else
					System.out.println(
							"Please, enter product ID if you want to add product to cart. Or enter 'checkout' if you want to proceed with checkout. Or enter 'menu' if you want to navigate back to the main menu.");
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** PRODUCT CATALOG *****");
		System.out.println(
				"Enter product id to add it to the cart or 'menu' if you want to navigate back to the main menu");
	}

	private String readUserInput() {
		System.out.print("Product ID to add to cart or enter 'checkout' to proceed with checkout: ");
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		return userInput;
	}

	private void printProductsToConsole() {
		var products = productManagementService.getProducts();
		for (var product : products) {
			System.out.println(product);
		}
	}

	private Product fetchProduct(String userInput) {
		// -? --> negative sign, could have none or one
		// \\d+ --> one or more digits
		if (userInput.matches("-?\\d+"))
			return productManagementService.getProductById(Integer.parseInt(userInput));
		return null;
	}

	private void processAddToCart(Product productToAddToCart) {
		context.getSessionCart().addProduct(productToAddToCart);
		System.out.printf(
				"Product %s has been added to your cart. " + "If you want to add a new product - enter the product id. "
						+ "If you want to proceed with checkout - enter word " + "'checkout' to console %n",
				productToAddToCart.getProductName());
	}

}
