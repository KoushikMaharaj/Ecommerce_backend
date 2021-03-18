package com.app.dto;

import lombok.Data;

@Data
public class SubCategoryDTO {
	private Integer id;
	private String subCtgName;

	public SubCategoryDTO(Integer id, String subCtgName) {
		super();
		this.id = id;
		this.subCtgName = subCtgName;
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getSubCtgName() {
		return subCtgName;
	}

	public void setSubCtgName(String subCtgName) {
		this.subCtgName = subCtgName;
	}

	@Override
	public String toString() {
		return "SubCategoryDTO [id=" + id + ", subCtgName=" + subCtgName + "]";
	}

}
