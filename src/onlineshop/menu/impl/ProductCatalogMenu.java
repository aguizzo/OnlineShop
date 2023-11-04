package onlineshop.menu.impl;

import java.util.Scanner;

import onlineshop.configs.ApplicationContext;
import onlineshop.enteties.Product;
import onlineshop.menu.Menu;
import onlineshop.services.ProductManagementService;
import onlineshop.services.impl.MySqlProductManagementService;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = MySqlProductManagementService.getInstance();
	}

	@Override
	public void start() {
		while (true) {
			printMenuHeader();
			printProductsToConsole();

			var userInput = readUserInput();

			if (context.getLoggedInUser() == null) {
				System.out.println(context.getString("not.logged.in.msg"));
				break;
			}

			if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND))
				break;

			if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				if (context.getSessionCart().isEmpty())
					System.out.println(context.getString("empty.cart.err.msg"));
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
					System.out.println(context.getString("enter.product.id"));
							
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("product.catalog.header"));
		System.out.println(	context.getString("catalog.cta"));
	}

	private String readUserInput() {
		System.out.print(context.getString("proceed.to.checkout"));
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
				context.getString("product.added.to.cart"),
				productToAddToCart.getProductName());
	}

}
