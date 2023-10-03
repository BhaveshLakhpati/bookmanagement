package com.bhaveshlakhapati.bookmanagement.eurekaregistryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaRegistryServerApplication {
	public static void main(final String[] args) {
		SpringApplication.run(EurekaRegistryServerApplication.class, args);
	}
}
