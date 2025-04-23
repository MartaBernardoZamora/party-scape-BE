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

@Component
public class MatchesWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, List<WebSocketSession>> matches = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String matchId = getQueryParam(session, "match");
    System.out.println("🔌 Nueva conexión. matchId = " + matchId);
    if (matchId == null) {
        System.out.println("❌ matchId es null. Cerrando conexión.");
        return;
    }
    matches.computeIfAbsent(matchId, k -> new CopyOnWriteArrayList<>()).add(session);
    System.out.println("✅ Sesión añadida a la partida " + matchId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String matchId = getQueryParam(session, "match");
        System.out.println("📩 Mensaje recibido. matchId = " + matchId + ", payload = " + message.getPayload());

        if (matchId == null) {
            System.out.println("❌ matchId null en mensaje. Ignorando.");
            return;
        }
        String payload = message.getPayload();


        for (WebSocketSession s : matches.get(matchId)) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(payload)); // lo reenvía tal cual
            }
        }
    }

    private String getQueryParam(WebSocketSession session, String key) {
        try {
            URI uri = session.getUri();
            if (uri == null) return null;
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