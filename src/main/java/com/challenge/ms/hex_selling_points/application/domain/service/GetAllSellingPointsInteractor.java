package com.challenge.ms.hex_selling_points.application.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(GetAllSellingPointsInteractor.class);

	@Override
	public Flux<SellingPoint> getAllSellingPoints() {
		logger.info("Intentando obtener todos los SellingPoints del cache.----------------------------");
		return sellingPointCacheOutputPort.getAllCachedSellingPoints().switchIfEmpty(Flux.defer(() -> {
			logger.info(
					"No se encontraron selling points en redis. Consultando la base de datos.----------------------------");
			return sellingPointRepositoryOutputPort.findAll().collectList().flatMapMany(sellingPointsFoundedOnDB -> {
				logger.info(
						"SellingPoints obtenidos de la base de datos. Guardando en el cache.----------------------------");
				sellingPointCacheOutputPort.saveAllSellingPointsToCache(Flux.fromIterable(sellingPointsFoundedOnDB))
						.subscribe();
				return Flux.fromIterable(sellingPointsFoundedOnDB);
			});
		}));
	}

}
