package onlineshop.services;

import java.util.List;

import onlineshop.enteties.Purchase;

public interface PurchaseManagementService {

	void addPurchase(Purchase purchase);

	List<Purchase> getPurchasesByUserId(int userId);
	
	List<Purchase> getPurchases();

}
