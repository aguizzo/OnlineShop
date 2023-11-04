package onlineshop.storage.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import onlineshop.enteties.Purchase;
import onlineshop.storage.OrderStoringService;

public class DefaultOrderStoringService implements OrderStoringService {

	private static final String ORDERS_STORAGE = "orders.data";
	private static final String FILE_STORAGE_FOLDER = "fileStorage";
	private static final String PATH = FILE_STORAGE_FOLDER + File.separator + ORDERS_STORAGE;

	private static DefaultOrderStoringService instance;

	private DefaultOrderStoringService() {
	}

	@Override
	public void saveOrders(List<Purchase> orders) {
		try (var oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
			oos.writeObject(orders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Purchase> loadOrders() {
		try (var ois = new ObjectInputStream(new FileInputStream(PATH))) {
			return (List<Purchase>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return Collections.emptyList() ;
		}

	}

	public static OrderStoringService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderStoringService();
		}
		return instance;
	}

}
