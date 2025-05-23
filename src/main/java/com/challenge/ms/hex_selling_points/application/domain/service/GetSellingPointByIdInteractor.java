package com.challenge.ms.hex_selling_points.application.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.GetSellingPointByIdInputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointCacheOutputPort;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointRepositoryOutputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetSellingPointByIdInteractor implements GetSellingPointByIdInputPort {

	private static final String REDIS_KEY_SELLING_POINT_KEY = "sellingPoint:";
	private final SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort;
	private final SellingPointCacheOutputPort sellingPointCacheOutputPort;
	private static final Logger logger = LoggerFactory.getLogger(GetSellingPointByIdInteractor.class);

	@Override
	public Mono<SellingPoint> getSellingPointById(Integer id) {
		logger.info("Intentando obtener un selling point por id.----------------------------");
		Mono<SellingPoint> redisValue = sellingPointCacheOutputPort.findByKey(REDIS_KEY_SELLING_POINT_KEY + id);
		Mono<SellingPoint> mongoValue = sellingPointRepositoryOutputPort.findById(id).flatMap(sellingPoint -> {
			logger.info(
					"No se encontraron selling points en redis. Consultando la base de datos----------------------------");
			return sellingPointCacheOutputPort.save(sellingPoint).thenReturn(sellingPoint);
		});
		return redisValue.switchIfEmpty(mongoValue);
	}
}
