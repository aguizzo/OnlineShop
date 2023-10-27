package onlineshop.storage;

import java.util.List;

import onlineshop.enteties.Order;

public interface OrderStoringService {
	
	void saveOrders(List<Order> order);
	
	List<Order> loadOrders();
}