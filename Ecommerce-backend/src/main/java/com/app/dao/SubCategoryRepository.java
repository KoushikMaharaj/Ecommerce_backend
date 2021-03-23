package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Category;
import com.app.pojos.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

	SubCategory findBySubCtgName(String subCtgName);

	List<SubCategory> findByCtg(Category ctg);
}
