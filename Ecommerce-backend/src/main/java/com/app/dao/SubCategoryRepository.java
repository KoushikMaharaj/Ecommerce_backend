package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
	@Query("SELECT distinct(s.subCtgName) from SubCategory s LEFT OUTER JOIN s.products p")
	List<String> getAllSubCategories();
	
	SubCategory findBySubCtgName(String subCtgName);
}
