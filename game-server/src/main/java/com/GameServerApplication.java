package com.camparena.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // <-- Isso é o que "liga" o nosso Game Loop (@Scheduled)
public class GameServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameServerApplication.class, args);
    }
}