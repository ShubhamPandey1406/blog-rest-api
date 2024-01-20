package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info= @Info(
				title="Spring Boot Blog App REST APIs",
				description = "Sring Boot Blog App REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name="Shubham",
						email="shwillshine@gmail.com",
						url="https//www.javaguides.net"
				),
				license = @License(
						name = "Apache 2.0",
						url="https//www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot blog application documentation",
				url="https://github.com/ShubhamPandey1406/blog-rest-api"
		)
)
public class BlogRestApiApplication {


	 @Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

}
