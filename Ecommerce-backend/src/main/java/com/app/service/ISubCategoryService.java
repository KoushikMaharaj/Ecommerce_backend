package com.app.service;

import java.util.List;

import com.app.pojos.SubCategory;

public interface ISubCategoryService {
	SubCategory addSubCategory(SubCategory subCtg);
	
	List<String> getAllSubCategories();
}
