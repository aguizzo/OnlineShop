package onlineshop.enteties.impl;

import java.util.Arrays;
import java.util.Objects;

import onlineshop.enteties.Cart;
import onlineshop.enteties.Product;

public class DefaultCart implements Cart {

	private final static int INITIAL_LENGTH = 10;
	private int lastIndex;
	private Product[] products;

	public DefaultCart() {
		lastIndex = 0;
		products = new Product[INITIAL_LENGTH];
	}

	@Override
	public boolean isEmpty() {
		if (products == null || products.length == 0) {
			return true;
		}
		for (Product product : products) {
			if (product != null)
				return false;
		}
		return true;
	}

	@Override
	public void addProduct(Product product) {
		if (product == null) {
			return;
		}
		if (products.length <= lastIndex) {
			products = Arrays.copyOf(products, products.length << 1);
		}
		products[lastIndex++] = product;
	}

	@Override
	public Product[] getProducts() {
		return Arrays.stream(products)
				.filter(Objects::nonNull)
				.toArray(Product[]::new);
	}

	@Override
	public void clear() {
		products = new Product[INITIAL_LENGTH];
		lastIndex = 0;
	}

}
