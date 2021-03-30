package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "products")
@CrossOrigin
public class Product extends BaseEntity {
	@Column(name = "product_name", length = 50, unique = true)
	private String prodName;

	@Column(name = "product_description")
	private String prodDesc;

	@Column(name = "product_warrenty", length = 10)
	private String prodWarrenty;

	@Column(name = "number_in_stock")
	private int numberInStock;

	@Column(name = "product_price")
	private double price;

	@Lob
	@Column(name = "product_image")
	private byte[] prodImage;

	private String imageFileName;

	@ManyToOne
	@JoinColumn(name = "subcategory_id", nullable = false)
	private SubCategory subCtg;

	public Product() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public Product(String prodName, String prodDesc, String prodWarrenty, int numberInStock, double price,
			byte[] prodImage) {
		super();
		this.prodName = prodName;
		this.prodDesc = prodDesc;
		this.prodWarrenty = prodWarrenty;
		this.numberInStock = numberInStock;
		this.price = price;
		this.prodImage = prodImage;
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

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}

	@Override
	public String toString() {
		return "Product [Id=" + getId() + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", prodWarrenty="
				+ prodWarrenty + ", numberInStock=" + numberInStock + ", price=" + price + ",    imageFileName="
				+ imageFileName + "]";
	}

}
