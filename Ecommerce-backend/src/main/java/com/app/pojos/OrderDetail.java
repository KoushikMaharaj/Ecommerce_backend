package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetail {
	@Column(name = "product_quantity")
	private int qty;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public OrderDetail() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public OrderDetail(int qty, double price) {
		super();
		this.qty = qty;
		this.totalPrice = price * qty;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderDetail [qty=" + qty + ", totalPrice=" + totalPrice + "]";
	}

	public void linkProduct(Product p) {
		product = p;
	}

}
