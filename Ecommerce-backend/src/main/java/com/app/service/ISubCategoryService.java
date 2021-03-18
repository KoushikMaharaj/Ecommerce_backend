package com.app.service;

import java.util.List;

import com.app.dto.SubCategoryDTO;
import com.app.pojos.SubCategory;

public interface ISubCategoryService {
	SubCategory addSubCategory(SubCategory subCtg);
	
	List<SubCategoryDTO> getAllSubCategories();
}
