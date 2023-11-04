package onlineshop.services.impl;

import java.util.List;

import onlineshop.dao.PurchaseDao;
import onlineshop.dao.impl.MySqlJdbcPurchaseDao;
import onlineshop.dto.PurchaseDto;
import onlineshop.dto.converter.PurchaseDtoToPurchaseConverter;
import onlineshop.enteties.Purchase;
import onlineshop.services.PurchaseManagementService;

public class MySqlPurchaseManagementService implements PurchaseManagementService {
	
	private static MySqlPurchaseManagementService instance;
	
	private PurchaseDao purchaseDao;
	private PurchaseDtoToPurchaseConverter converter;
	
	private MySqlPurchaseManagementService() {
		purchaseDao = new MySqlJdbcPurchaseDao();
		converter = new PurchaseDtoToPurchaseConverter();
	}
	
	public static MySqlPurchaseManagementService getInstance() {
		if (instance == null)
			instance = new MySqlPurchaseManagementService();
		return instance;
	}
	
	@Override
	public void addPurchase(Purchase purchase) {
		purchaseDao.savePurchase(converter.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public List<Purchase> getPurchasesByUserId(int userId) {
		List<PurchaseDto> purchases = purchaseDao.getPurchasesByUserId(userId);
		return converter.convertPurchaseDtosToPurchases(purchases);
	}

	@Override
	public List<Purchase> getPurchases() {
		List<PurchaseDto> purchases = purchaseDao.getPurchases();
		return converter.convertPurchaseDtosToPurchases(purchases);
	}

}
