package com.example.eler.test.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		final Join join = new Join();
		SpringApplication.run(Application.class, args);
		join.callAll();
	}

}
