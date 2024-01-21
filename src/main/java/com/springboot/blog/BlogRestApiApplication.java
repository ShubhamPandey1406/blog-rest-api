package com.springboot.blog;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class BlogRestApiApplication implements CommandLineRunner {


	 @Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

	@Autowired
   private RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {

		Role adminRole= new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole= new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);


	}
}
