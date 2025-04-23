package com.challenge.ms.hex_selling_points.application.domain.service;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.GetSellingPointByIdInputPort;

import reactor.core.publisher.Mono;

public class GetSellingPointByIdInteractor implements GetSellingPointByIdInputPort {

//	private final SellingPointRepositoryOutputPort sellingPointRepositoryOutputPort;
//	private final SellingPointCacheOutputPort sellingPointCacheOutputPort;
//agregarlombok
	@Override
	public Mono<SellingPoint> getSellingPointById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
