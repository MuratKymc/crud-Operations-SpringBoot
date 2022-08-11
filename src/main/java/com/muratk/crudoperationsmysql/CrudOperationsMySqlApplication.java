package com.muratk.crudoperationsmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.muratk.crudoperationsmysql.repository")
public class CrudOperationsMySqlApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CrudOperationsMySqlApplication.class, args);
	}
	
}
