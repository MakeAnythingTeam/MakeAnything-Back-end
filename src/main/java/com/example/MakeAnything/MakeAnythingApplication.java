package com.example.MakeAnything;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MakeAnythingApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.properties,"
			+ "classpath:aws.yml,"
			+ "classpath:application-local.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(MakeAnythingApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
