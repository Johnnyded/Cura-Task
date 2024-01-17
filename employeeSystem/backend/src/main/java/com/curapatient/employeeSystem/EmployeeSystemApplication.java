package com.curapatient.employeeSystem;

import com.curapatient.employeeSystem.Model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.curapatient.*")
public class EmployeeSystemApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeSystemApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSystemApplication.class, args);
	}

}
