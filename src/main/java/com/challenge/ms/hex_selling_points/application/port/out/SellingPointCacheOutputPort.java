package com.challenge.ms.hex_selling_points.application.port.out;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SellingPointCacheOutputPort {

	Mono<SellingPoint> save(SellingPoint sellingPoint);

	Mono<SellingPoint> findByKey(String id);

	Mono<Void> clearCache();

	Mono<Void> saveInitialData();// a revisar si queda

	// para getAllSellingPoints
	Flux<SellingPoint> getAllCachedSellingPoints();

	// para getAllSellingPoints
	Mono<Void> saveAllSellingPointsToCache(Flux<SellingPoint> sellingPoints);

	Mono<Long> delete(String redisKey);
}
