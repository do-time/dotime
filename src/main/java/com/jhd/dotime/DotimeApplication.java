package com.jhd.dotime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DotimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DotimeApplication.class, args);
	}

}
