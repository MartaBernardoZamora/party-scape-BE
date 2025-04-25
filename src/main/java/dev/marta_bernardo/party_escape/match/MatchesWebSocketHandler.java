package dev.marta_bernardo.party_escape.match;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class MatchesWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, List<WebSocketSession>> matches = new ConcurrentHashMap<>();
    private final Map<String, List<String>> matchPlayers = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String matchId = getQueryParam(session, "match");

        if (matchId == null) return;
        
        matches.computeIfAbsent(matchId, k -> new CopyOnWriteArrayList<>()).add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String matchId = getQueryParam(session, "match");

        if (matchId == null) return;

        JsonNode json = objectMapper.readTree(message.getPayload());
        String type = json.get("type").asText();

        if ("GET_PLAYERS".equals(type)) {
            List<Map<String, String>> players = matchPlayers.getOrDefault(matchId, List.of())
                    .stream()
                    .map(name -> Map.of("playerName", name))
                    .toList();

            ObjectNode response = objectMapper.createObjectNode();
            response.put("type", "PLAYER_LIST");
            response.set("payload", objectMapper.valueToTree(players));

            session.sendMessage(new TextMessage(response.toString()));
            return;
        }

        if ("NEW_PLAYER_JOINED".equals(type)) {
            String playerName = json.get("payload").get("playerName").asText();
            matchPlayers.computeIfAbsent(matchId, k -> new CopyOnWriteArrayList<>()).add(playerName);
        }

        for (WebSocketSession s : matches.get(matchId)) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(message.getPayload()));
            }
        }
    }

    private String getQueryParam(WebSocketSession session, String key) {
        try {
            URI uri = session.getUri();
            if (uri == null)
                return null;
            String[] params = uri.getQuery().split("&");
            for (String param : params) {
                String[] kv = param.split("=");
                if (kv.length == 2 && kv[0].equals(key)) {
                    return URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            System.err.println("Error leyendo query param: " + e.getMessage());
        }
        return null;
    }
}