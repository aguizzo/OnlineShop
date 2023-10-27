package onlineshop.storage.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import onlineshop.enteties.Product;
import onlineshop.enteties.impl.DefaultProduct;
import onlineshop.storage.ProductStoringService;

public class DefaultProductStoringService implements ProductStoringService {
	
	private static final String PRODUCTS_STORAGE = "products.txt";
	private static final String FILE_STORAGE_FOLDER = "fileStorage";
	private static final int PRODUCT_PRICE_INDEX = 3;
	private static final int PRODUCT_CATEGORY_INDEX = 2;
	private static final int PRODUCT_NAME_INDEX = 1;
	private static final int PRODUCT_ID_INDEX = 0;
	
	private static DefaultProductStoringService instance;

	@Override
	public List<Product> loadProducts() {
		Path path = Paths.get(FILE_STORAGE_FOLDER, PRODUCTS_STORAGE);
		try {
			return Files.lines(path)
				.filter(Objects::nonNull)
				.filter(line -> !line.isEmpty())
				.map(line -> line.split(","))
				.map(productElements -> new DefaultProduct(
						Integer.parseInt(productElements[PRODUCT_ID_INDEX]), 
						productElements[PRODUCT_NAME_INDEX], 
						productElements[PRODUCT_CATEGORY_INDEX], 
						Double.parseDouble(productElements[PRODUCT_PRICE_INDEX])))
				.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public static DefaultProductStoringService getInstance() {
		if (instance == null) {
			instance = new DefaultProductStoringService();
		}
		return instance;
	}
	
//	public static void main(String[] args) throws IOException {
//		var service = DefaultProductStoringService.getInstance();
//		var list = service.loadProducts();
//	}
}
