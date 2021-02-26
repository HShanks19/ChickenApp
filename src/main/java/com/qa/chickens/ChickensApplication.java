package com.qa.chickens;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.qa.rest")
public class ChickensApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChickensApplication.class, args);		
		
	}
	
}
