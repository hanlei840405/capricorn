package com.galaxy.framework.capricorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
public class CapricornApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapricornApplication.class, args);
	}
}
