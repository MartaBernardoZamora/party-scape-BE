package dev.marta_bernardo.party_escape.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

import dev.marta_bernardo.party_escape.match.MatchesWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final MatchesWebSocketHandler handler;

    public WebSocketConfig(MatchesWebSocketHandler handler) {
        this.handler = handler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/api/v1/ws-matches").setAllowedOriginPatterns("*");
    }

}

