package com.camparena.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampArenaApplication {

    public static void main(String[] args) {
        // Starts the standalone Spring Boot server
        SpringApplication.run(CampArenaApplication.class, args);
    }
}