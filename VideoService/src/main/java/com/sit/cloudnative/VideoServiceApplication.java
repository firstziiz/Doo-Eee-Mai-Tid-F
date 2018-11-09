package com.sit.cloudnative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableJpaAuditing
@CrossOrigin("*")
public class VideoServiceApplication {
	public static void main(String[] args) {SpringApplication.run(VideoServiceApplication.class, args);}
}
