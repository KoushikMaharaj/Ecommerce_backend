package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryRepository;
import com.app.pojos.Category;

@Service
@Transactional
public class ImplCategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository ctgRepo;

	@Override
	public Category addCategory(Category ctg) {
		return ctgRepo.save(ctg);
	}

	@Override
	public List<Category> getAllCategories() {
		return ctgRepo.findAll();
	}

}
