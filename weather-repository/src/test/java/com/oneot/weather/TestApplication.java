package com.oneot.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.oneot.*")
@ComponentScan({ "com.oneot.*" })
@EntityScan("com.oneot.*")
@PropertySource(value = {"repository.properties" })
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class);
	}

}
