package com.app.dto;

public class ProductUpdateRequestDTO {
	private Integer id;
	private int price;
	private int numberInStock;

	public ProductUpdateRequestDTO() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public ProductUpdateRequestDTO(Integer id, int numberInStock, int price) {
		super();
		this.id = id;
		this.numberInStock = numberInStock;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductUpdateRequestDTO [id=" + id + ", numberInStock=" + numberInStock + ", price=" + price + "]";
	}
}
