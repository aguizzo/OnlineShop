package onlineshop.dao;

import java.util.List;

import onlineshop.dto.PurchaseDto;

public interface PurchaseDao {
	
	void savePurchase(PurchaseDto order);

	List<PurchaseDto> getPurchasesByUserId(int userId);
	
	List<PurchaseDto> getPurchases();
}
