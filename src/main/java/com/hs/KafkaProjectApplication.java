package com.hs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;

import com.hs.kafka.HsProducerController;

@SpringBootApplication(scanBasePackages={
		"com.hs.config", "com.hs.pojo","com.hs.kafka"},scanBasePackageClasses = HsProducerController.class)

public class KafkaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectApplication.class, args);
	}

}
