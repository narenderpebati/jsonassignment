package com.json.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan("com.json.startup")
public class JsonAssignmentApplication {

	
	public static void main(String[] args)  {
		SpringApplication.run(JsonAssignmentApplication.class, args);
		

	    }
		
}