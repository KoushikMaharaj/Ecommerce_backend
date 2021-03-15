package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.SubCategoryRepository;
import com.app.pojos.SubCategory;

@Service
@Transactional
public class ImplSubCategory implements ISubCategoryService {
	@Autowired
	private SubCategoryRepository subCtgRepo;

	@Override
	public SubCategory addSubCategory(SubCategory subCtg) {
		return subCtgRepo.save(subCtg);
	}

	@Override
	public List<SubCategory> getAllSubCategories() {
		return subCtgRepo.findAll();
	}

}
