package com.challenge.ms.hex_selling_points.adapter.out.cache.redis;

import java.time.Duration;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;

import com.challenge.ms.hex_selling_points.application.domain.model.SellingPoint;
import com.challenge.ms.hex_selling_points.application.port.out.SellingPointCacheOutputPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RedisSellingPointCacheAdapter implements SellingPointCacheOutputPort {

	private static final String REDIS_KEY_SELLING_POINTS_LIST = "sellingPointsList";
	private static final String REDIS_KEY_SELLING_POINT_PREFIX = "sellingPoint:";
	private static final int REDIS_TTL_SHORT = 15;

	private ReactiveRedisTemplate<String, SellingPoint> redisTemplate;
	private ReactiveValueOperations<String, SellingPoint> redisValOperations;

	@Override
	public Mono<SellingPoint> save(SellingPoint sellingPoint) {
		return redisValOperations.set(REDIS_KEY_SELLING_POINT_PREFIX + sellingPoint.getId(), sellingPoint,
				Duration.ofSeconds(REDIS_TTL_SHORT)).thenReturn(sellingPoint);
	}

	@Override
	public Mono<SellingPoint> findByKey(String id) {
		return redisValOperations.get(id);
	}

	@Override
	public Mono<Void> clearCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> saveInitialData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<SellingPoint> getAllCachedSellingPoints() {
		return redisTemplate.getExpire(REDIS_KEY_SELLING_POINTS_LIST).flatMapMany(expire -> {
			return redisTemplate.opsForList().range(REDIS_KEY_SELLING_POINTS_LIST, 0, -1);
		});
	}

	@Override
	public Mono<Void> saveAllSellingPointsToCache(Flux<SellingPoint> sellingPoints) {
		return sellingPoints
				.flatMap(list -> redisTemplate.opsForList().rightPushAll(REDIS_KEY_SELLING_POINTS_LIST, list)
						.then(redisTemplate.expire(REDIS_KEY_SELLING_POINTS_LIST, Duration.ofSeconds(REDIS_TTL_SHORT))))
				.then();
	}

	@Override
	public Mono<Long> delete(String redisKey) {
		return redisTemplate.delete(redisKey);
	}

}
