package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.SubCategory;
import com.app.service.ISubCategoryService;

@RestController
@RequestMapping("/subcategory")
@CrossOrigin
public class SubCategoryController {

	@Autowired
	private ISubCategoryService subCtgService;

	public SubCategoryController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@PostMapping
	public ResponseEntity<?> addSubCategory(@RequestBody SubCategory subCategory) {
		try {
			return new ResponseEntity<>(subCtgService.addSubCategory(subCategory), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public List<SubCategory> getAllSubCategories() {
		return subCtgService.getAllSubCategories();
	}
}
