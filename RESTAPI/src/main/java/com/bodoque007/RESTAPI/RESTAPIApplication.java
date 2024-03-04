package com.bodoque007.RESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class RESTAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RESTAPIApplication.class, args);
	}

}
