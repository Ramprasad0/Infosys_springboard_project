package com.example.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Module2Application {
	public static void main(String[] args) {
		SpringApplication.run(Module2Application.class, args);
		System.out.println("Module2 Application started...");
	}
}
