package com.springboot.fixedTermAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioAccountFixedTermApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioAccountFixedTermApplication.class, args);
	}

}
