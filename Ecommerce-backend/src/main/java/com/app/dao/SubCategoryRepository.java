package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
	SubCategory findBySubCtgName(String subCtgName);
}
