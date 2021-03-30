package com.app.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
@CrossOrigin
public class User extends BaseEntity {
	@Column(length = 20)
	private String userName;

	@Column(length = 30, unique = true)
	private String userEmail;

	@Column(length = 10)
	private String userPassword;

	@Embedded
	@Column(name = "user_address", length = 100)
	private Address userAddr;

	@Column(length = 10)
	private String userContact;

	@Column(name = "user_role", length = 10)
	@Enumerated(EnumType.STRING)
	private Role role;

	@JsonIgnore
	@OneToOne(mappedBy = "custCart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;

	public User() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public User(String userName, String userEmail, String userPassword, Address userAddr, String userContact,
			Role role) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAddr = userAddr;
		this.userContact = userContact;
		this.role = role;
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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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

	@JsonIgnore
	public Cart getCart() {
		return cart;
	}

	@JsonProperty
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [Id=" + getId() + ", userName=" + userName + ", userEmail=" + userEmail + ", userAddr=" + userAddr
				+ ", userContact=" + userContact + ", role=" + role + "]";
	}

	public void addCart(Cart c) {
		cart = c;
		c.setCustCart(this);
	}

	public void removeCart(Cart c) {
		cart = null;
		c.setCustCart(null);
	}

	public void linkOrders(Order o) {
		o.setCustomer(this);
	}

	public void unlinkOrders(Order o) {

		o.setCustomer(null);
	}

}
