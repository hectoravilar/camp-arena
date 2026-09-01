package com.camparena.server.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MatchSocketHandler extends TextWebSocketHandler {

    // Thread-safe map to keep track of all active player connections
    private final Map<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Generate a unique ID for the new player
        String playerId = UUID.randomUUID().toString();
        
        // Store the session attributes and keep track of it
        session.getAttributes().put("playerId", playerId);
        activeSessions.put(session.getId(), session);

        System.out.println("New player connected! Session ID: " + session.getId() + " | Player ID: " + playerId);

        // TODO: In the future, this is where we will send the initial MatchState (ball position, team)
        String welcomeMessage = String.format("{\"type\": \"SPAWN\", \"playerId\": \"%s\"}", playerId);
        session.sendMessage(new TextMessage(welcomeMessage));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // This method receives the inputs from the player (e.g., joystick movements, shoot button)
        String payload = message.getPayload();
        String playerId = (String) session.getAttributes().get("playerId");
        
        System.out.println("Received input from " + playerId + ": " + payload);
        
        // TODO: Pass the input to the Physics Engine to update the game state
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove the player from the active sessions when they disconnect
        String playerId = (String) session.getAttributes().get("playerId");
        activeSessions.remove(session.getId());
        
        System.out.println("Player disconnected. Session ID: " + session.getId() + " | Player ID: " + playerId);
    }
}