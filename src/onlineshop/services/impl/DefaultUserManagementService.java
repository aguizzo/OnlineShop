package onlineshop.services.impl;

import java.util.ArrayList;
import java.util.List;

import onlineshop.enteties.User;
import onlineshop.services.UserManagementService;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	
	
	private static DefaultUserManagementService instance;
	
	private List<User> users;

	private DefaultUserManagementService() {
		users = new ArrayList<User>();
	}
	
	@Override
	public String registerUser(User user) {
		if (user == null)
			return NO_ERROR_MESSAGE;
		
		String errorMessage = checkUniqueEmail(user.getEmail());
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}
		
		users.add(user);
		
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	
	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
//		return users.stream()
//				.filter(Objects::nonNull)
//				.filter(user -> user.getEmail() == userEmail)
//				.findFirst().orElse(null);
		for (User user : users) {
			if (user != null && user.getEmail().equalsIgnoreCase(userEmail))
				return user;
		}
		return null;
	}
	
	private String checkUniqueEmail(String email) {
		if (email == null || email.isEmpty()) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		}
		for (User user : users) {
			if (user != null && 
					user.getEmail() != null &&
					user.getEmail().equalsIgnoreCase(email)) {
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}
		}
		return NO_ERROR_MESSAGE;
	}
	
	void clearServiceState() {
		users.clear();
	}
}
