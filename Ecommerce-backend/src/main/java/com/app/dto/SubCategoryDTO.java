package com.app.dto;

import lombok.Data;

@Data
public class SubCategoryDTO {

	private String subCtgName;

	public SubCategoryDTO(String subCtgName) {
		super();

		this.subCtgName = subCtgName;
	}

	public String getSubCtgName() {
		return subCtgName;
	}

	public void setSubCtgName(String subCtgName) {
		this.subCtgName = subCtgName;
	}

	@Override
	public String toString() {
		return "SubCategoryDTO [subCtgName=" + subCtgName + "]";
	}


}
