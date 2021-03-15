package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
	@Column(name = "product_name", length = 50)
	private String prodName;

	@Column(name = "product_description")
	private String prodDesc;

	@Column(name = "product_warrenty", length = 10)
	private String prodWarrenty;

	@Column(name = "product_stock_status")
	private boolean prodStockStatus = true;

	@Column(name = "product_price")
	private double price;

	@Lob
	@Column(name = "product_image")
	private byte[] prodImage;

	@ManyToOne
	@JoinColumn(name = "subcategory_id", nullable = false)
	private SubCategory subCtg;

	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>();

	/*
	 * @OneToMany(mappedBy = "product") private List<OrderDetail> details = new
	 * ArrayList<>();
	 */

	public Product() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public Product(String prodName, String prodDesc, String prodWarrenty, boolean prodStockStatus, double price) {
		super();
		this.prodName = prodName;
		this.prodDesc = prodDesc;
		this.prodWarrenty = prodWarrenty;
		this.prodStockStatus = prodStockStatus;
		this.price = price;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdWarrenty() {
		return prodWarrenty;
	}

	public void setProdWarrenty(String prodWarrenty) {
		this.prodWarrenty = prodWarrenty;
	}

	public boolean isProdStockStatus() {
		return prodStockStatus;
	}

	public void setProdStockStatus(boolean prodStockStatus) {
		this.prodStockStatus = prodStockStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SubCategory getSubCtg() {
		return subCtg;
	}

	public void setSubCtg(SubCategory subCtg) {
		this.subCtg = subCtg;
	}

	public byte[] getProdImage() {
		return prodImage;
	}

	public void setProdImage(byte[] prodImage) {
		this.prodImage = prodImage;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	/*
	 * public List<OrderDetail> getDetails() { return details; }
	 * 
	 * public void setDetails(List<OrderDetail> details) { this.details = details; }
	 */

	@Override
	public String toString() {
		return "Product [getId()=" + getId() + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", prodWarrenty="
				+ prodWarrenty + ", prodStockStatus=" + prodStockStatus + ", price=" + price + "]";
	}

}
