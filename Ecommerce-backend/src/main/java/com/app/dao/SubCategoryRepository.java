package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.SubCategoryDTO;
import com.app.pojos.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
		
	SubCategory findBySubCtgName(String subCtgName);
}
