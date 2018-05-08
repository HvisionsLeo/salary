package com.drop.salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SalaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalaryApplication.class, args);
		try {
			Runtime.getRuntime().exec("cmd /c start http://localhost:8080");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
