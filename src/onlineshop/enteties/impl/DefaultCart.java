package onlineshop.enteties.impl;

import java.util.ArrayList;
import java.util.List;

import onlineshop.enteties.Cart;
import onlineshop.enteties.Product;

public class DefaultCart implements Cart {
	private List<Product> products;

	public DefaultCart() {
		products = new ArrayList<Product>();
	}

	@Override
	public boolean isEmpty() {
		return products.isEmpty();
	}

	@Override
	public void addProduct(Product product) {
		if (product == null) {
			return;
		}
		products.add(product);
	}

	@Override
	public List<Product> getProducts() {
		return this.products;
	}

	@Override
	public void clear() {
		products.clear();
	}

}
