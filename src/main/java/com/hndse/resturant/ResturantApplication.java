package com.hndse.resturant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Resturant API", version = "1.0"))
public class ResturantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResturantApplication.class, args);
	}

}
