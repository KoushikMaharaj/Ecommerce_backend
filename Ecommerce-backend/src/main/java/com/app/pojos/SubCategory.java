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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "subcategories")
public class SubCategory extends BaseEntity {
	@Column(name = "subcategory_name", length = 30)
	private String subCtgName;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category ctg;

	@JsonIgnore
	@OneToMany(mappedBy = "subCtg", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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

	@JsonIgnore
	public List<Product> getProducts() {
		return products;
	}

	@JsonProperty
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "SubCategory [Id=" + getId() + ", subCtgName=" + subCtgName + "]";
	}

	public void addProduct(Product p) {
		products.add(p);
		p.setSubCtg(this);
	}

	public void removeProduct(Product p) {
		products.remove(p);
		p.setSubCtg(null);
	}

}
