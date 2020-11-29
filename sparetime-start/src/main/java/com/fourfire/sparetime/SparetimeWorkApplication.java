package com.fourfire.sparetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SparetimeWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SparetimeWorkApplication.class, args);
	}

}
