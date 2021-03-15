package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User custCart;

	@Column(name = "product_quantity")
	private int qty;

	@Column(name="total_price")
	private double totalPrice;

	@ManyToMany
	@JoinTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_ids"))
	private List<Product> products = new ArrayList<>();

	public Cart() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public User getCustCart() {
		return custCart;
	}

	public void setCustCart(User custCart) {
		this.custCart = custCart;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
