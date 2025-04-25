package com.challenge.ms.hex_selling_points.application.domain.service;

import org.springframework.stereotype.Service;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.CreateSellingPointInputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointCacheOutputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointRepositoryOutputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateSellingPointInteractor implements CreateSellingPointInputPort {

	private static final String REDIS_KEY_SELLING_POINTS_LIST = "sellingPointsList";
	private final SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort;
	private final SellingPointCacheOutputPort sellingPointCacheOutputPort;

	@Override
	public Mono<SellingPoint> createSellingPoint(SellingPoint sellingPoint) {
		return sellingPointRepositoryOutputPort.findById(sellingPoint.getId())
				.flatMap(existingSellingPoint -> Mono.<SellingPoint>empty())
				.switchIfEmpty(sellingPointRepositoryOutputPort.save(sellingPoint)
						.flatMap(savedSellingPoint -> sellingPointCacheOutputPort.save(savedSellingPoint)
								.then(sellingPointCacheOutputPort.delete(REDIS_KEY_SELLING_POINTS_LIST))
								.then(sellingPointRepositoryOutputPort.findAll().collectList()
										.flatMap(sellingPointsList -> sellingPointCacheOutputPort
												.saveAllSellingPointsToCache(Flux.fromIterable(sellingPointsList))))
								.thenReturn(savedSellingPoint)));
	}
}
