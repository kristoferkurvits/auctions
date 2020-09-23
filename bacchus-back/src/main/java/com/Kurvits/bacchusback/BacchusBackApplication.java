package com.Kurvits.bacchusback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BacchusBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BacchusBackApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String url = "http://localhost:9090";
				String url2 = "http://127.0.0.1:9090";
				registry.addMapping("/users").allowedOrigins(url, url2);
				registry.addMapping("/api").allowedOrigins(url, url2);
			}
		};
	}

}
