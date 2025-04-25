package com.challenge.ms.hex_selling_points.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteSellingPointInputPort {

	Mono<Long> deleteSellingPoint(Integer id);

}
