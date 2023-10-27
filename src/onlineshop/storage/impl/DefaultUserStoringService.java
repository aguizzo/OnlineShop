package onlineshop.storage.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import onlineshop.enteties.User;
import onlineshop.enteties.impl.DefaultUser;
import onlineshop.storage.UserStoringService;

public class DefaultUserStoringService implements UserStoringService {
	
	private static final String USERS_STORAGE = "users.txt";
	private static final String FILE_STORAGE_FOLDER = "fileStorage";
	private static final int USER_EMAIL_INDEX = 4;
	private static final int USER_PASSWORD_INDEX = 3;
	private static final int USER_LASTNAME_INDEX = 2;
	private static final int USER_FIRSTNAME_INDEX = 1;
	private static final int USER_ID_INDEX = 0;

	private static DefaultUserStoringService instance;
	@Override
	public void storeUser(User user) {
		Path path = Paths.get(FILE_STORAGE_FOLDER, USERS_STORAGE);
		try {
			Files.writeString(path, convertToStorableString(user), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String convertToStorableString(User user) {
		return user.getId() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + ","
				+ user.getEmail() + "\n";
	}

	@Override
	public List<User> loadUsers() {
		Path path = Paths.get(FILE_STORAGE_FOLDER, USERS_STORAGE);
		try {
			return Files.lines(path)
				.filter(Objects::nonNull)
				.filter(line -> !line.isEmpty())
				.map(line -> line.split(","))
				.map(userElements -> new DefaultUser(
						Integer.parseInt(userElements[USER_ID_INDEX]),
						userElements[USER_FIRSTNAME_INDEX],
						userElements[USER_LASTNAME_INDEX],
						userElements[USER_PASSWORD_INDEX],
						userElements[USER_EMAIL_INDEX]))
				.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public static DefaultUserStoringService getInstance() {
		if (instance == null) {
			instance = new DefaultUserStoringService();
		}
		return instance;
	}
	
	public static void main(String[] args) throws IOException {
		var service = DefaultUserStoringService.getInstance();
		var list = service.loadUsers();
		System.out.println(list);
}

}
