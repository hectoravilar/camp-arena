package com.camparena.server.model;

public class Player {
    private String id;
    private double x;
    private double y;

    // Default constructor
    public Player() {
    }

    // Constructor matching the (String, double, double) format requested by
    // MatchManager
    public Player(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}