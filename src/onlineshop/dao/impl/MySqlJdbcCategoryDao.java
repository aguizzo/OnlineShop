package onlineshop.dao.impl;

import java.sql.SQLException;

import onlineshop.dao.CategoryDao;
import onlineshop.dto.CategoryDto;
import onlineshop.utils.db.DBUtils;

public class MySqlJdbcCategoryDao implements CategoryDao {

	@Override
	public CategoryDto getCategoryByCategoryId(int id) {
		String query = "SELECT * FROM category WHERE id = ?";
		try(var connection = DBUtils.getConnection();
				var ps = connection.prepareStatement(query)){
			ps.setInt(1, id);
			
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					var category = new CategoryDto();
					category.setId(rs.getInt("id"));
					category.setCategoryName(rs.getString("category_name"));
					return category;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
