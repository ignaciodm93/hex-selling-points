package com.challenge.ms.hex_selling_points.application.domain.service;

import org.springframework.stereotype.Service;

import com.challenge.ms.hex_selling_points.application.port.in.DeleteSellingPointInputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointCacheOutputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointRepositoryOutputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteSellingPointInteractor implements DeleteSellingPointInputPort {

	private static final String REDIS_KEY_SELLING_POINT_KEY = "sellingPoint:";
	private final SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort;
	private final SellingPointCacheOutputPort sellingPointCacheOutputPort;

	@Override
	public Mono<Long> deleteSellingPoint(Integer id) {
		return sellingPointRepositoryOutputPort.findById(id)
				.flatMap(existingSellingPoint -> sellingPointRepositoryOutputPort.deleteById(id)
						.then(sellingPointCacheOutputPort.delete(REDIS_KEY_SELLING_POINT_KEY + id))
						.then(sellingPointRepositoryOutputPort.findAll().collectList().flatMap(sellingPointsList -> {
							return sellingPointCacheOutputPort
									.saveAllSellingPointsToCache(Flux.fromIterable(sellingPointsList));
						})).thenReturn(1L))
				.switchIfEmpty(Mono.just(0L));
	}

}
