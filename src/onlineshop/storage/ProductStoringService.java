package onlineshop.storage;

import java.util.List;

import onlineshop.enteties.Product;

public interface ProductStoringService {
	List<Product> loadProducts();
}
