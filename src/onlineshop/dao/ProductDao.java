package onlineshop.dao;

import java.util.List;

import onlineshop.dto.ProductDto;

public interface ProductDao {
	
	List<ProductDto> getProducts();

	ProductDto getProductById(int productId);
}
