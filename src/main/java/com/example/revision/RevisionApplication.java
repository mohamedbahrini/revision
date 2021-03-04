package com.example.revision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
@PropertySource("classpath:config.properties")
public class RevisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevisionApplication.class, args);
	}

}
