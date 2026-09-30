package com.camparena.server.model;

public class Player {
    private String id;
    private String name;

    // Default constructor required by frameworks like Spring and Jackson (for JSON
    // parsing)
    public Player() {
    }

    // Constructor to initialize a new player with specific data
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters to access and modify private properties safely
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}