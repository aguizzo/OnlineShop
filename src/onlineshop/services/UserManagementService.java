package onlineshop.services;

import java.util.List;

import onlineshop.enteties.User;

public interface UserManagementService {

	String registerUser(User user);
	
	List<User> getUsers();

	User getUserByEmail(String userEmail);
	
	void resetPasswordForUser(User user);

}
