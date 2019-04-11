package com.example.cloudCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CloudCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudCacheApplication.class, args);
	}

}
