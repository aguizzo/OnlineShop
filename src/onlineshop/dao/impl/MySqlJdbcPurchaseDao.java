package onlineshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import onlineshop.dao.ProductDao;
import onlineshop.dao.PurchaseDao;
import onlineshop.dao.UserDao;
import onlineshop.dto.ProductDto;
import onlineshop.dto.PurchaseDto;
import onlineshop.dto.UserDto;
import onlineshop.utils.db.DBUtils;

public class MySqlJdbcPurchaseDao implements PurchaseDao {

	private UserDao userDao;
	private ProductDao productDao;
	
	public MySqlJdbcPurchaseDao() {
		userDao = new MySqlJdbcUserDao();
		productDao = new MySqlJdbcProductDao();
	}
	
	@Override
	public void savePurchase(PurchaseDto purchase) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("INSERT INTO purchase (fk_purchase_user) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
				var psPurchaseProduct = conn.prepareStatement("INSERT INTO purchase_product (purchase_id, product_id) VALUES (?, ?)")) {
			
			ps.setInt(1, purchase.getUserDto().getId());
			ps.executeUpdate();
			
			try (var generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	
	            	for (ProductDto product : purchase.getProductDtos()) {
	            		psPurchaseProduct.setLong(1, generatedKeys.getLong(1));
	            		psPurchaseProduct.setInt(2, product.getId());
	            		psPurchaseProduct.addBatch();
	            	}
	            	
	            	psPurchaseProduct.executeBatch();
	            }
	            else {
	                throw new SQLException("Creating purchase failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PurchaseDto> getPurchasesByUserId(int userId) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase WHERE fk_purchase_user = ?")) {
			ps.setInt(1, userId);
			
			List<PurchaseDto> purchases = new ArrayList<>();
			
			try (var rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchaseDto purchase = new PurchaseDto();
					purchase.setId(rs.getInt("id"));
					purchase.setUserDto(userDao.getUserById(rs.getInt("fk_purchase_user")));
					
					List<ProductDto> products = new ArrayList<>();
					try (var psProducts = conn.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
							var rsProducts = psProducts.executeQuery()) {
						
						while (rsProducts.next()) {
							products.add(productDao.getProductById(rsProducts.getInt("product_id")));
						}
					}
					purchase.setProductDtos(products);
					purchases.add(purchase);
				}
			}
			return purchases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<PurchaseDto> getPurchases() {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase")) {
			
			List<PurchaseDto> purchases = new ArrayList<>();
			
			try (var rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchaseDto purchase = new PurchaseDto();
					purchase.setId(rs.getInt("id"));
					UserDto userById = userDao.getUserById(rs.getInt("fk_purchase_user"));
					purchase.setUserDto(userById);
					
					List<ProductDto> products = new ArrayList<>();
					try (var psProducts = conn.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
							var rsProducts = psProducts.executeQuery()) {
						
						while (rs.next()) {
							products.add(productDao.getProductById(rs.getInt("product_id")));
						}
					}
					purchase.setProductDtos(products);
					purchases.add(purchase);
				}
			}
			
			return purchases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
