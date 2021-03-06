package com.starbank.model.entity;

import org.springframework.stereotype.Component;

import com.starbank.exceptions.AddressException;
import com.starbank.exceptions.IdException;
import com.starbank.exceptions.InvalidEgnException;
import com.starbank.exceptions.InvalidEmailException;
import com.starbank.exceptions.InvalidNameException;
import com.starbank.exceptions.InvalidPasswordException;
import com.starbank.exceptions.InvalidPhoneNumberException;
import com.starbank.exceptions.InvalidStringException;
import com.starbank.validators.EgnValidator;
import com.starbank.validators.EmailValidator;
import com.starbank.validators.IValidator;
import com.starbank.validators.NameValidator;
import com.starbank.validators.PhoneNumberValidator;

@Component
public class User {

	private int userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;
	private String address;
	private boolean isAdmin;
	private boolean isRegistered;
	private String egn;
	private boolean isLiked;
	
	public User() {
	}

	public User(int userId, String firstName, String middleName, String lastName, String phoneNumber, String email,
			String password, String address, String egn, boolean isAdmin, boolean isRegistered, boolean isLiked)
			throws IdException, InvalidNameException, InvalidPhoneNumberException, InvalidEmailException,
			AddressException, InvalidStringException, InvalidPasswordException, InvalidEgnException {

		if (IValidator.isPositive(userId)) {
			this.userId = userId;
		} else {
			throw new IdException("Invalid user ID");
		}
		if (NameValidator.isValidName(firstName)) {
			this.firstName = firstName;
		}
		if (NameValidator.isValidName(middleName)) {
			this.middleName = middleName;
		}
		if (NameValidator.isValidName(lastName)) {
			this.lastName = lastName;
		}
		if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
		if (EmailValidator.isValidEmail(email)) {
			this.email = email;
		}
		this.password = password;
		if (IValidator.isValidString(address)) {
			this.address = address;
		} else {
			throw new AddressException("Incorrect address");
		}
		if (EgnValidator.isValidEgn(egn)) {
			this.egn = egn;
		}
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public String getEgn() {
		return egn;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public void setEgn(String egn) {
		this.egn = egn;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", isAdmin=" + isAdmin + ", isRegistered=" + isRegistered + ", egn=" + egn
				+ ", isLiked=" + isLiked + "]";
	}

}
