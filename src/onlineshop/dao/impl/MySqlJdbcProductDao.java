package onlineshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineshop.dao.CategoryDao;
import onlineshop.dao.ProductDao;
import onlineshop.dto.CategoryDto;
import onlineshop.dto.ProductDto;
import onlineshop.dto.UserDto;
import onlineshop.utils.db.DBUtils;

public class MySqlJdbcProductDao implements ProductDao {
	// Table column names
	private static String ID = "id";
	private static String PRODUCT_NAME = "product_name";
	private static String PRICE = "price";
	private static String CATEGORY = "fk_category_id";
	
	private CategoryDao categoryDao;
	
	public MySqlJdbcProductDao () {
		categoryDao = new MySqlJdbcCategoryDao();
	}

	@Override
	public List<ProductDto> getProducts() {
		String query = "SELECT * FROM product";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {

			try (var rs = ps.executeQuery()) {
				List<ProductDto> products = new ArrayList<ProductDto>();
				while (rs.next())
					products.add(getProduct(rs));
				return products;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductDto getProductById(int productId) {
		String query = "SELECT * FROM product WHERE "+ ID +" = ?";
		try(var connection = DBUtils.getConnection();
				var ps = connection.prepareStatement(query)){
			ps.setInt(1, productId);
			try(var rs = ps.executeQuery()){
				if (rs.next())
					return getProduct(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ProductDto getProduct(ResultSet rs) throws SQLException {
		var producDto = new ProductDto();
		
		producDto.setId(rs.getInt(ID));
		producDto.setProductName(rs.getString(PRODUCT_NAME));
		producDto.setCategoryDto(categoryDao.getCategoryByCategoryId(rs.getInt(CATEGORY)));
		producDto.setPrice(rs.getBigDecimal(PRICE));
	
		return producDto;
	}

}
