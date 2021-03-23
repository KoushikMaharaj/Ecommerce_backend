package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnore
	private User custCart;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_ids"))
	private List<Product> products = new ArrayList<>();

	public Cart() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@JsonIgnore
	public User getCustCart() {
		return custCart;
	}

	@JsonProperty
	public void setCustCart(User custCart) {
		this.custCart = custCart;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product prod) {
		products.add(prod);
		// prod.getCarts().add(this);
	}

	public void removeProduct(Product prod) {
		products.remove(prod);
		// prod.getCarts().remove(this);
	}

}
