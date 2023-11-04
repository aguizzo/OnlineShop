package onlineshop.storage;

import java.util.List;

import onlineshop.enteties.Purchase;

public interface OrderStoringService {
	
	void saveOrders(List<Purchase> order);
	
	List<Purchase> loadOrders();
}