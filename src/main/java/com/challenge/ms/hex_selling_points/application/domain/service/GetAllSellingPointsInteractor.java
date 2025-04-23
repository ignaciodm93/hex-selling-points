package com.challenge.ms.hex_selling_points.application.domain.service;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.GetAllSellingPointsInputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointCacheOutputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointRepositoryOutputPort;

import reactor.core.publisher.Flux;

public class GetAllSellingPointsInteractor implements GetAllSellingPointsInputPort {

	private final SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort;
	private final SellingPointCacheOutputPort sellingPointCacheOutputPort;

	public GetAllSellingPointsInteractor(SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort,
			SellingPointCacheOutputPort sellingPointCacheOutputPort) {
		this.sellingPointRepositoryOutputPort = sellingPointRepositoryOutputPort;
		this.sellingPointCacheOutputPort = sellingPointCacheOutputPort;
	}

	// Consulto la cache de redis, si hay algo lo devuelvo, si no hay nada lo busco
	// en la base, actualizo cache y lo devuelvo.
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
