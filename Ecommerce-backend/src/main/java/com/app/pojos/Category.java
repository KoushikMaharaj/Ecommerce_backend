package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
	@Column(name = "category_name", length = 20, unique = true)
	private String ctgName;

	@OneToMany(mappedBy = "ctg", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubCategory> subCategories = new ArrayList<>();

	public Category() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public Category(String ctgName) {
		super();
		this.ctgName = ctgName;
	}

	public String getCtgName() {
		return ctgName;
	}

	public void setCtgName(String ctgName) {
		this.ctgName = ctgName;
	}

	@Override
	public String toString() {
		return "Category [Id=" + getId() + ", ctgName=" + ctgName + "]";
	}

	public void addSubCtg(SubCategory subCtg) {
		subCategories.add(subCtg);
		subCtg.setCtg(this);
	}

	public void removeSubCtg(SubCategory subCtg) {
		subCategories.remove(subCtg);
		subCtg.setCtg(null);
	}

}
