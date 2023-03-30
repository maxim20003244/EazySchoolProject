package com.eazybyte.springschoolproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class EasySchoolApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasySchoolApplication.class, args);
	}

}
