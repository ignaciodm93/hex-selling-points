package com.challenge.ms.hex_selling_points.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteSellingPointInputPort {

	Mono<Void> deleteSellingPoint(Integer id);

}
