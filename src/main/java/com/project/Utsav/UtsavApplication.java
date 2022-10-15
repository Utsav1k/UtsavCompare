package com.project.Utsav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UtsavApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtsavApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {

	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins(
	                            "http://192.168.1.12:3000",
	                            "http://localhost:3000",
	                            "http://localhost:1111");
	        }
	    };
	}

}
