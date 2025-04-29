package com.challenge.ms.hex_selling_points.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.CreateSellingPointInputPort;
import com.challenge.ms.hex_selling_points.application.port.in.DeleteSellingPointInputPort;
import com.challenge.ms.hex_selling_points.application.port.in.GetAllSellingPointsInputPort;
import com.challenge.ms.hex_selling_points.application.port.in.GetSellingPointByIdInputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/selling-points")
public class SellingPointController {

	private final GetAllSellingPointsInputPort getAllSellingPointsInputPort;
	private final GetSellingPointByIdInputPort getSellingPointByIdInputPort;
	private final CreateSellingPointInputPort createSellingPointInputPort;
	private final DeleteSellingPointInputPort deleteSellingPointInputPort;

	@GetMapping
	public Flux<SellingPoint> getAllSellingPoints() {
		return getAllSellingPointsInputPort.getAllSellingPoints();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<SellingPoint>> getSellingPointById(@PathVariable Integer id) {
		return getSellingPointByIdInputPort.getSellingPointById(id).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<SellingPoint>> createSellingPoint(@RequestBody SellingPoint sellingPoint) {
		return createSellingPointInputPort.createSellingPoint(sellingPoint)
				.map(savedSellingPoint -> ResponseEntity.status(HttpStatus.CREATED).body(savedSellingPoint))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteSellingPoint(@PathVariable Integer id) {
		return deleteSellingPointInputPort.deleteSellingPoint(id).map(result -> {
			if (result == 1L) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		});
	}

//
//	@PutMapping("/{id}")
//	public Mono<ResponseEntity<SellingPoint>> updateSellingPoint(@PathVariable Integer id,
//			@RequestBody SellingPoint sellingPoint) {
//		return sellingPointService.update(id, sellingPoint)
//				.map(updatedSellingPoint -> ResponseEntity.ok(updatedSellingPoint))
//				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)));
//	}
//
////	@PostMapping("/initial-data")
////	public Mono<ResponseEntity<Void>> saveInitialData() {
////		return sellingPointService.saveInitialData().then(Mono.just(ResponseEntity.ok().build()));
////	}
//
//	@DeleteMapping("/flush-all")
//	public Mono<ResponseEntity<Void>> clearCache() {
//		return sellingPointService.clearCache().then(Mono.just(ResponseEntity.noContent().build()));
//	}
}