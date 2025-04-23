package com.challenge.ms.hex_selling_points.adapter.in.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sellingPoints")
public class SellingPointMongoDocument {

	@Id
	private Integer id;
	private String name;

	public SellingPointMongoDocument() {
	}

	public SellingPointMongoDocument(Integer id, String name) {
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