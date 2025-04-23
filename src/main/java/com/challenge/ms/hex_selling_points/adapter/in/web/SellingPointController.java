package com.challenge.ms.hex_selling_points.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.in.GetAllSellingPointsInputPort;
import com.challenge.ms.hex_selling_points.application.port.in.GetSellingPointByIdInputPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/selling-points")
public class SellingPointController {

//	@Autowired
//	private SellingPointService sellingPointService;
//	
//	si

	private final GetAllSellingPointsInputPort getAllSellingPointsInputPort;
	private final GetSellingPointByIdInputPort getSellingPointByIdInputPort;

	public SellingPointController(GetAllSellingPointsInputPort getAllSellingPointsInputPort,
			GetSellingPointByIdInputPort getSellingPointByIdInputPort) {
		this.getAllSellingPointsInputPort = getAllSellingPointsInputPort;
		this.getSellingPointByIdInputPort = getSellingPointByIdInputPort;
	}

	@GetMapping
	public Flux<SellingPoint> getAllSellingPoints() {
		return getAllSellingPointsInputPort.getAllSellingPoints();
	}
//
//	reutilizo el getAllSellingPoints
//	@GetMapping("/{id}")
//	public Mono<ResponseEntity<SellingPoint>> getSellingPointById(@PathVariable Integer id) {
//		return sellingPointService.findById(id).map(ResponseEntity::ok)
//				.defaultIfEmpty(ResponseEntity.notFound().build());
//	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<SellingPoint>> getSellingPointById(@PathVariable Integer id) {
//		return getSellingPointByIdInputPort.getSellingPointById(id);
		return null;
	}

//	si
//	@PostMapping
//	public Mono<ResponseEntity<SellingPoint>> createSellingPoint(@RequestBody SellingPoint sellingPoint) {
//		return sellingPointService.findById(sellingPoint.getId())
//				.flatMap(existingSellingPoint -> Mono
//						.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SellingPoint())))
//				.switchIfEmpty(sellingPointService.save(sellingPoint)
//						.map(savedSellingPoint -> ResponseEntity.status(HttpStatus.CREATED).body(savedSellingPoint)))
//				.map(responseEntity -> {
//					if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
//						return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//					}
//					return responseEntity;
//				});
//	}
//
//	@PutMapping("/{id}")
//	public Mono<ResponseEntity<SellingPoint>> updateSellingPoint(@PathVariable Integer id,
//			@RequestBody SellingPoint sellingPoint) {
//		return sellingPointService.update(id, sellingPoint)
//				.map(updatedSellingPoint -> ResponseEntity.ok(updatedSellingPoint))
//				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)));
//	}
//
//	@DeleteMapping("/{id}")
//	public Mono<ResponseEntity<Void>> deleteSellingPoint(@PathVariable Integer id) {
//		return sellingPointService.deleteById(id).map(result -> {
//			if (result == 1L) {
//				return ResponseEntity.status(HttpStatus.OK).build();
//			} else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//			}
//		});
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