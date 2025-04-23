package com.challenge.ms.hex_selling_points.application.port.in;

import reactor.core.publisher.Mono;

public interface SaveInitialDataInputPort {

	Mono<Void> saveInitialData();
	// tal vez lo elimine

}
