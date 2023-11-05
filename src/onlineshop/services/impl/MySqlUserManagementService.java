package onlineshop.services.impl;

import java.util.List;

import onlineshop.dao.UserDao;
import onlineshop.dao.impl.MySqlJdbcUserDao;
import onlineshop.dto.converter.UserDtoToUserConverter;
import onlineshop.enteties.User;
import onlineshop.services.UserManagementService;
import onlineshop.utils.mail.MailSender;
import onlineshop.utils.mail.impl.DefaultMailSender;

public class MySqlUserManagementService implements UserManagementService {
	
	private static MySqlUserManagementService instance;
	public static final String SUCCESSFULL_REGISTRATION_MESSAGE = "User is registered!";
	private static final String REGISTRATION_ERROR_MESSAGE = "The email is already in use by other user.";
	
	private UserDao userDao;
	private UserDtoToUserConverter userConverter;
	
	private MailSender mailSender;
	
	private MySqlUserManagementService() {
		userDao = new MySqlJdbcUserDao();
		userConverter = new UserDtoToUserConverter();
		mailSender = DefaultMailSender.getInstance();
	}
	
	public static MySqlUserManagementService getInstance() {
		if (instance == null)
			instance = new MySqlUserManagementService();
		return instance;
	}
	
	@Override
	public String registerUser(User user) {
		boolean isCreated = userDao.saveUser(userConverter.convertUserToUserDto(user));
		if (isCreated)
			return SUCCESSFULL_REGISTRATION_MESSAGE;
		else
			return REGISTRATION_ERROR_MESSAGE;
	}

	@Override
	public List<User> getUsers() {
		var users = userDao.getUsers();
		return userConverter.convertUserDtosToUsers(users);
	}

	@Override
	public User getUserByEmail(String userEmail) {
		var user = userDao.getUserByEmail(userEmail);
		return userConverter.convertUserDtoToUser(user);
	}

	@Override
	public void resetPasswordForUser(User user) {
		mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
	}

}
