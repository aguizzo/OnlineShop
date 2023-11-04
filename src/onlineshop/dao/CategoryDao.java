package onlineshop.dao;

import onlineshop.dto.CategoryDto;

public interface CategoryDao {
	
	CategoryDto getCategoryByCategoryId(int id);
	
}
