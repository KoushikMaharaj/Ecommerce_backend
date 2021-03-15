package com.app.service;

import java.util.List;

import com.app.pojos.Category;

public interface ICategoryService {
	Category addCategory(Category ctg);

	List<Category> getAllCategories();
}
