package com.challenge.ms.hex_selling_points.application.domain.model;

public class SellingPoint {

	private Integer id;
	private String name;

	public SellingPoint(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
