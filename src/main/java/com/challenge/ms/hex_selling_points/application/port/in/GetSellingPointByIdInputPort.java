package com.challenge.ms.hex_selling_points.application.port.in;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;

import reactor.core.publisher.Mono;

public interface GetSellingPointByIdInputPort {
	Mono<SellingPoint> getSellingPointById(Integer id);
}
