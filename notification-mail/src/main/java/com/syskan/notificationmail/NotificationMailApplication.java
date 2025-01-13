package com.syskan.notificationmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationMailApplication.class, args);
	}

}
