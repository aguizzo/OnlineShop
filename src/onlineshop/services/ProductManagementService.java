package onlineshop.services;

import java.util.List;

import onlineshop.enteties.Product;

public interface ProductManagementService {

	List<Product> getProducts();

	Product getProductById(int productIdToAddToCart);

}
