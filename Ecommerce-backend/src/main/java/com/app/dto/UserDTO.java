package com.app.dto;

import com.app.pojos.Address;
import com.app.pojos.Role;

public class UserDTO {

	private Integer id;
	private String userName;
	private String userEmail;
	private Address userAddr;
	private String userContact;
	private Role role;

	public UserDTO() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public UserDTO(Integer id, String userName, String userEmail, Address userAddr, String userContact, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userAddr = userAddr;
		this.userContact = userContact;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Address getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(Address userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userName=" + userName + ", userEmail=" + userEmail + ", userAddr=" + userAddr
				+ ", userContact=" + userContact + ", role=" + role + "]";
	}

}
