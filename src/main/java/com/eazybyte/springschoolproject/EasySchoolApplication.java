package com.eazybyte.springschoolproject;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eazybyte.springschoolproject.repository")
@EntityScan("com.eazybyte.springschoolproject.model")
public class EasySchoolApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasySchoolApplication.class, args);
	}

}
