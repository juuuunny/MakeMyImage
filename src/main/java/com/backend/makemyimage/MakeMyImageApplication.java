package com.backend.makemyimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MakeMyImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakeMyImageApplication.class, args);
    }

}
