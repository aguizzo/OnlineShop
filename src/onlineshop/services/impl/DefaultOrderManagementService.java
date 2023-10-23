package onlineshop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import onlineshop.enteties.Order;
import onlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static DefaultOrderManagementService instance;
	private List<Order> orders;
	
	public DefaultOrderManagementService() {
		orders = new ArrayList<Order>();
	}
	
	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null)
			return;
		orders.add(order);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return orders.stream()
				.filter(Objects::nonNull)
				.filter(o -> o.getCustomerId() == userId)
				.toList();
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}
	
	void clearServiceState() {
		orders.clear();
	}

}
