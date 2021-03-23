package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryRepository;
import com.app.dao.SubCategoryRepository;
import com.app.pojos.Category;
import com.app.pojos.SubCategory;

@Service
@Transactional
public class ImplSubCategory implements ISubCategoryService {
	@Autowired
	private SubCategoryRepository subCtgRepo;
	@Autowired
	private CategoryRepository ctgRepo;

	@Override
	public SubCategory addSubCategory(SubCategory subCtg) {
		Category ctg = ctgRepo.findByCtgName(subCtg.getCtg().getCtgName());
		ctg.addSubCtg(subCtg);
		return subCtgRepo.save(subCtg);
	}

	@Override
	public List<SubCategory> getAllSubCategories() {
		return subCtgRepo.findAll();
	}

	@Override
	public List<SubCategory> getSubCategoryByCategory(String ctgName) {
		Category ctg = ctgRepo.findByCtgName(ctgName);
		return subCtgRepo.findByCtg(ctg);
	}

	@Override
	public SubCategory getSubCategoryByName(String name) {
		
		return subCtgRepo.findBySubCtgName(name);
	}

}
