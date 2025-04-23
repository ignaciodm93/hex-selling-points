package com.challenge.ms.hex_selling_points.adapter.in.model;

public class RedisTemplateConfig {

//	@Bean
//	public ReactiveRedisTemplate<String, Map<Integer, Integer>> reactiveRedisTemplateSellingCost(
//			ReactiveRedisConnectionFactory factory) {
//		StringRedisSerializer keySerializer = new StringRedisSerializer();
//
//		JavaType valueType = TypeFactory.defaultInstance().constructMapType(Map.class, Integer.class, Integer.class);
//		Jackson2JsonRedisSerializer<Map<Integer, Integer>> valueSerializer = new Jackson2JsonRedisSerializer<>(
//				valueType);
//
//		RedisSerializationContext<String, Map<Integer, Integer>> serializationContext = RedisSerializationContext
//				.<String, Map<Integer, Integer>>newSerializationContext(keySerializer).value(valueSerializer).build();
//
//		return new ReactiveRedisTemplate<>(factory, serializationContext);
//	}
//
//	@Bean
//	public ReactiveRedisTemplate<String, Map<String, Object>> reactiveRedisTemplateCheapestPath(
//			ReactiveRedisConnectionFactory factory) {
//		StringRedisSerializer keySerializer = new StringRedisSerializer();
//
//		JavaType valueType = TypeFactory.defaultInstance().constructMapType(Map.class, String.class, Object.class);
//		Jackson2JsonRedisSerializer<Map<String, Object>> valueSerializer = new Jackson2JsonRedisSerializer<>(valueType);
//
//		RedisSerializationContext<String, Map<String, Object>> serializationContext = RedisSerializationContext
//				.<String, Map<String, Object>>newSerializationContext(keySerializer).value(valueSerializer).build();
//
//		return new ReactiveRedisTemplate<>(factory, serializationContext);
//	}

}
