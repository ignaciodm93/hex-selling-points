package com.challenge.ms.hex_selling_points;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HexSellingPointsApplication {
	public static void main(String[] args) {
		SpringApplication.run(HexSellingPointsApplication.class, args);
	}
}
