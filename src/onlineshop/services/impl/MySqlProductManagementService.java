package onlineshop.services.impl;

import java.util.List;

import onlineshop.dao.ProductDao;
import onlineshop.dao.impl.MySqlJdbcProductDao;
import onlineshop.dto.converter.ProductDtoToProductConverter;
import onlineshop.enteties.Product;
import onlineshop.services.ProductManagementService;

public class MySqlProductManagementService implements ProductManagementService {
	
	private static MySqlProductManagementService instance;
	private ProductDao productDao;
	private ProductDtoToProductConverter converter;
	
	private MySqlProductManagementService () {
		productDao = new MySqlJdbcProductDao();
		converter = new ProductDtoToProductConverter();
	}
	
	public static MySqlProductManagementService getInstance() {
		if (instance == null)
			instance = new MySqlProductManagementService();
		return instance;
	}
	
	@Override
	public List<Product> getProducts() {
		var productsDtos = productDao.getProducts();
		return converter.convertProductDtosToProducts(productsDtos);
	}

	@Override
	public Product getProductById(int productIdToAddToCart) {
		var product = productDao.getProductById(productIdToAddToCart);
		return converter.convertProductDtoToProduct(product);
	}

}
