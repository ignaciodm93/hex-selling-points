package com.challenge.ms.hex_selling_points.adapter.out.persistence.mongodb;

import org.springframework.stereotype.Component;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointRepositoryOutputPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MongoSellingPointRepositoryAdapter implements SellingPointRepositoryOutputPort {

	private final MongoSellingPointRepository mongoSellingPointRepository;

	public MongoSellingPointRepositoryAdapter(MongoSellingPointRepository mongoSellingPointRepository) {
		this.mongoSellingPointRepository = mongoSellingPointRepository;
	}

	@Override
	public Mono<SellingPoint> save(SellingPoint sellingPoint) {
		return mongoSellingPointRepository.save(SellingPointMongoMapper.fromDomainToDbModel(sellingPoint))
				.map(SellingPointMongoMapper::fromDbToDomainModel);
	}

	@Override
	public Mono<SellingPoint> findById(Integer id) {
		return mongoSellingPointRepository.findById(id).map(SellingPointMongoMapper::fromDbToDomainModel);
	}

	@Override
	public Flux<SellingPoint> findAll() {
		return mongoSellingPointRepository.findAll().map(SellingPointMongoMapper::fromDbToDomainModel);
	}

	@Override
	public Mono<Void> deleteById(Integer id) {
		return mongoSellingPointRepository.deleteById(id);
	}

}
