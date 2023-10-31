package validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import onlineshop.enteties.User;
import onlineshop.enteties.impl.DefaultUser;
import onlineshop.utils.validation.Validator;
import onlineshop.utils.validation.impl.DefaultValidator;

class ValidatorTest {
	
	private Validator testInstance;
	private User user;
	
	@BeforeEach
	void setUp() {
		testInstance = new DefaultValidator();
	}

	@Test
	void shouldValidateFirstNameWithoutAnyNumbersWithLatinCharsOnly() {
		user = new DefaultUser("Andres", "Guizzo", "qwerty", "email@email.com");
		assertTrue(testInstance.isValid(user));
	}
	
	@Test
	void shouldFailValidationFirstNameWithDigits() {
		user = new DefaultUser("4ndres", "Guizzo", "qwerty", "email@email.com");
		assertFalse(testInstance.isValid(user));
	}
	
	@Test
	void shouldValidateLastNameWithoutAnyNumbersWithLatinCharsOnly() {
		user = new DefaultUser("Andres", "Guizzo", "qwerty", "email@email.com");
		assertTrue(testInstance.isValid(user));
	}
	
	@Test
	void shouldFailValidationLastNameWithDigits() {
		user = new DefaultUser("Andres", "Guizzo22", "qwerty", "email@email.com");
		assertFalse(testInstance.isValid(user));
	}
	
	@Test
	void shouldValidateEmailWithAtSign() {
		user = new DefaultUser("Andres", "Guizzo", "qwerty", "email@email.com");
		assertTrue(testInstance.isValid(user));
	}

}
