package com.challenge.ms.hex_selling_points.application.domain.service;

import org.springframework.stereotype.Service;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.GetAllSellingPointsInputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointCacheOutputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointRepositoryOutputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetAllSellingPointsInteractor implements GetAllSellingPointsInputPort {

	private final SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort;
	private final SellingPointCacheOutputPort sellingPointCacheOutputPort;

	@Override
	public Flux<SellingPoint> getAllSellingPoints() {
		return sellingPointCacheOutputPort.getAllCachedSellingPoints().switchIfEmpty(
				sellingPointRepositoryOutputPort.findAll().collectList().flatMapMany(sellingPointsFoundedOnDB -> {
					sellingPointCacheOutputPort
							.saveAllSellingPointsToCache(Flux.fromIterable(sellingPointsFoundedOnDB));
					return Flux.fromIterable(sellingPointsFoundedOnDB);
				}));
	}

}
