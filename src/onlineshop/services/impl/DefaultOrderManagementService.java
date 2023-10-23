package onlineshop.services.impl;

import java.util.Arrays;
import java.util.Objects;

import onlineshop.enteties.Order;
import onlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {
	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;
	private Order[] orders;
	private int lastIndex;
	
	public DefaultOrderManagementService() {
		orders = new Order[DEFAULT_ORDER_CAPACITY];
		lastIndex = 0;
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
		if (lastIndex >= orders.length)
			orders = Arrays.copyOf(orders, orders.length << 1);
		orders[lastIndex++] = order;
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		return Arrays.stream(orders)
				.filter(Objects::nonNull)
				.filter(o -> o.getCustomerId() == userId)
				.toArray(Order[]::new);
	}

	@Override
	public Order[] getOrders() {
		return Arrays.stream(orders)
				.filter(Objects::nonNull)
				.toArray(Order[]::new);
	}
	
	void clearServiceState() {
		lastIndex = 0;
		orders = new Order[DEFAULT_ORDER_CAPACITY];
	}

}
