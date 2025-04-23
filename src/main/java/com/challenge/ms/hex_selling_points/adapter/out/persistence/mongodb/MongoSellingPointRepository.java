package com.challenge.ms.hex_selling_points.adapter.out.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.challenge.ms.hex_selling_points.adapter.in.model.SellingPointMongoDocument;

public interface MongoSellingPointRepository extends ReactiveMongoRepository<SellingPointMongoDocument, Integer> {

}
