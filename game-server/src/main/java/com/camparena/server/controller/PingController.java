package com.camparena.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller to handle incoming HTTP requests
@RestController
public class PingController {

    // Maps the HTTP GET request at the "/ping" endpoint to this specific method
    @GetMapping("/ping")
    public String checkStatus() {
        return "Camp Arena Server is Online and running!";
    }
}