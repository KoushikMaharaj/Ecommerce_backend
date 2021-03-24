package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	@DateTimeFormat(pattern = "yyyy-dd-mm")
	private LocalDate orderDate = LocalDate.now();

	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderDetail> details = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User customer;

	@Embedded
	private Address orderAddress;

	public Order() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public Address getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(Address orderAddress) {
		this.orderAddress = orderAddress;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

}
