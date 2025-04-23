package com.challenge.ms.hex_selling_points.adapter.out.persistence.mongodb;

import com.challenge.ms.hex_selling_points.adapter.in.model.SellingPointMongoDocument;
import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;

public class SellingPointMongoMapper {

	public static SellingPoint fromDbToDomainModel(SellingPointMongoDocument document) {
		return new SellingPoint(document.getId(), document.getName());
	}

	public static SellingPointMongoDocument fromDomainToDbModel(SellingPoint sellingPoint) {
		return new SellingPointMongoDocument(sellingPoint.getId(), sellingPoint.getName());
	}
}
