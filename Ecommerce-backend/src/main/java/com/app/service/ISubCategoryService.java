package com.app.service;

import java.util.List;

import com.app.pojos.SubCategory;

public interface ISubCategoryService {
	SubCategory addSubCategory(SubCategory subCtg);

	List<SubCategory> getAllSubCategories();

	List<SubCategory> getSubCategoryByCategory(String ctgName);

	SubCategory getSubCategoryByName(String name);
}
