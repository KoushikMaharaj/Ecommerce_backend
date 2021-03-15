package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subcategories")
public class SubCategory extends BaseEntity {
	@Column(name = "subcategory_name", length = 30)
	private String subCtgName;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category ctg;

	@OneToMany(mappedBy = "subCtg", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<>();

	public SubCategory() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public SubCategory(String subCtgName) {
		super();
		this.subCtgName = subCtgName;
	}

	public String getSubCtgName() {
		return subCtgName;
	}

	public void setSubCtgName(String subCtgName) {
		this.subCtgName = subCtgName;
	}

	public Category getCtg() {
		return ctg;
	}

	public void setCtg(Category ctg) {
		this.ctg = ctg;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "SubCategory [Id=" + getId() + ", subCtgName=" + subCtgName + "]";
	}

	// helper method to link products
	public void addProduct(Product p) {
		// subctg---->product
		products.add(p);
		// product---->subctg
		p.setSubCtg(this);
	}

	// helper method to unlink products
	public void removeProduct(Product p) {
		// subctg-X--->product
		products.remove(p);
		// product-X--->subctg
		p.setSubCtg(null);
	}

}
