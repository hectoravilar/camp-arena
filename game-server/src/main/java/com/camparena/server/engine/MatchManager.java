package com.camparena.server.engine;

import com.camparena.server.model.Player;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MatchManager {     

    // Thread-safe maps for players and active WebSocket sessions
    private final Map<String, Player> players = new ConcurrentHashMap<>();
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // 1. Connection Management
    public void addPlayer(String playerId, WebSocketSession session) {
        // Spawn the player at the center of the field (0,0)
        players.put(playerId, new Player(playerId, 0.0, 0.0));
        sessions.put(playerId, session);
    }

    public void removePlayer(String playerId) {
        players.remove(playerId);
        sessions.remove(playerId);
    }

    // 2. Input Processing
    public void processInput(String playerId, String action) {
        Player player = players.get(playerId);
        if (player == null) return;

        // Basic physics update based on input
        double speed = 5.0;
        switch (action) {
            case "UP" -> player.setY(player.getY() + speed);
            case "DOWN" -> player.setY(player.getY() - speed);
            case "LEFT" -> player.setX(player.getX() - speed);
            case "RIGHT" -> player.setX(player.getX() + speed);
        }
    }

    // 3. The Game Loop (Tick Rate)
    // fixedRate = 33 means this runs every 33 milliseconds (~30 times per second)
    @Scheduled(fixedRate = 33)
    public void gameTick() {
        if (players.isEmpty()) return; // Don't waste CPU if the stadium is empty

        // Build a very simple JSON string to represent the game state
        // In a real scenario, use Jackson ObjectMapper to serialize the whole Map
        StringBuilder stateJson = new StringBuilder("{ \"type\": \"STATE_UPDATE\", \"players\": [");
        players.values().forEach(p -> {
            stateJson.append(String.format("{\"id\":\"%s\", \"x\":%f, \"y\":%f},", p.getId(), p.getX(), p.getY()));
        });
        
        // Remove trailing comma and close JSON
        if (stateJson.charAt(stateJson.length() - 1) == ',') {
            stateJson.deleteCharAt(stateJson.length() - 1);
        }
        stateJson.append("] }");

        TextMessage message = new TextMessage(stateJson.toString());

        // Broadcast to all active sessions
        sessions.values().forEach(session -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                System.err.println("Error broadcasting to session: " + e.getMessage());
            }
        });
    }
}