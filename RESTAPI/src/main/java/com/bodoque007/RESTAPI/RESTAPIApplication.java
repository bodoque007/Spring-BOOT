package com.bodoque007.RESTAPI;

import com.bodoque007.RESTAPI.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class RESTAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RESTAPIApplication.class, args);
	}

}
