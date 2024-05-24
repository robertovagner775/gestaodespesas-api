package com.roberto.gestaodespesas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaodespesasApplication {

	public String  PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(GestaodespesasApplication.class, args);
	}

}
