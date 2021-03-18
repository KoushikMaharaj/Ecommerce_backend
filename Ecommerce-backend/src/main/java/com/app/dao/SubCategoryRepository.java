package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.SubCategoryDTO;
import com.app.pojos.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
	@Query("SELECT new com.app.dto.SubCategoryDTO(s.id,s.subCtgName) from SubCategory s LEFT OUTER JOIN s.products p")
	List<SubCategoryDTO> getAllSubCategories();
	
	SubCategory findBySubCtgName(String subCtgName);
}
