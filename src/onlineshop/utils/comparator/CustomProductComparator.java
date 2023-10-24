package onlineshop.utils.comparator;

import java.util.Comparator;

import onlineshop.enteties.Product;

public class CustomProductComparator implements Comparator<Product> {

	@Override
	public int compare(Product product1, Product product2) {
		int result = product1.getCategoryName().compareTo(product2.getCategoryName());
		if (result == 0) {
			double difference = product1.getPrice() - product2.getPrice();
			result = difference < 0 ? -1 : (difference == 0) ? 0 : 1;
		}
		if (result == 0)
			result = product1.getProductName().compareTo(product2.getProductName());
			
		return result;
	}

}
