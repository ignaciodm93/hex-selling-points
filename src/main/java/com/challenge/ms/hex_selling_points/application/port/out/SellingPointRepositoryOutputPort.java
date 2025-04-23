package com.challenge.ms.hex_selling_points.application.port.out;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SellingPointRepositoryOutputPort {

	Mono<SellingPoint> save(SellingPoint sellingPoint);

	Mono<SellingPoint> findById(Integer id);

	Flux<SellingPoint> findAll();

	Mono<Void> deleteById(Integer id);

}
