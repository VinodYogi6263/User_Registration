package com.ss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class User_Registration_Application 
{
	private static final Logger log = LoggerFactory.getLogger(User_Registration_Application.class);


	public static void main(String[] args)
	{
		SpringApplication.run(User_Registration_Application.class, args);
		
		log.debug("Spring boot Application run");
		
		log.info("Run Succssfully User_Registration_Application main method");
		
	}
}
