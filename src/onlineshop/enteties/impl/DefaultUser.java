package onlineshop.enteties.impl;

import onlineshop.enteties.User;

public class DefaultUser implements User {

	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	private static int nextId = 1;
	
	
	public DefaultUser() {
		this.id = nextId++;
	}
	
	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	@Override
	public String getFirstName() {
		// <write your code here>
		return firstName;
	}

	@Override
	public String getLastName() {
		// <write your code here>
		return lastName;
	}

	@Override
	public String getPassword() {
		// <write your code here>
		return password;
	}

	@Override
	public String getEmail() {
		// <write your code here>
		return email;
	}

	@Override
	public String toString() {
		return "First Name: " + this.getFirstName() + "\t\t" +
				"Last Name: " + this.getLastName() + "\t\t" +
				"Email: " + this.getEmail();
	}
	@Override
	public void setPassword(String password) {
		if (password != null)
			this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		if (newEmail != null)
			this.email = newEmail;
	}

	@Override
	public int getId() {
		// <write your code here>
		return id;
	}
	
	void clearState() {
		nextId = 1;
	}
}
