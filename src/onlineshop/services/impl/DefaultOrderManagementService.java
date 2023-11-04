package onlineshop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import onlineshop.enteties.Purchase;
import onlineshop.services.PurchaseManagementService;
import onlineshop.storage.OrderStoringService;
import onlineshop.storage.impl.DefaultOrderStoringService;

public class DefaultOrderManagementService implements PurchaseManagementService {

	private static DefaultOrderManagementService instance;
	private List<Purchase> orders;
	private OrderStoringService orderStoringService;
	
	{
		orderStoringService = DefaultOrderStoringService.getInstance();
		orders = orderStoringService.loadOrders();
		if (orders.isEmpty())
			orders = new ArrayList<Purchase>();
	}
	
	public static PurchaseManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addPurchase(Purchase order) {
		if (order == null) {
			return;
		}
		orders.add(order);
		orderStoringService.saveOrders(orders);
	}

	@Override
	public List<Purchase> getPurchasesByUserId(int userId) {
		return orderStoringService.loadOrders().stream()
				.filter(Objects::nonNull)
				.filter(order -> order.getCustomerId() == userId)
				.collect(Collectors.toList());
	}

	@Override
	public List<Purchase> getPurchases() {
		if (orders == null || orders.size() == 0) {
			orders = orderStoringService.loadOrders();
		}
		return this.orders;
	}
	
	void clearServiceState() {
		orders.clear();
	}

}
