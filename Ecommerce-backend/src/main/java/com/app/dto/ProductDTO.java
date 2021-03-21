package com.app.dto;

public class ProductDTO {
	private Integer id;

	public ProductDTO(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + "]";
	}

}
