package com.chit.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSocketMessageBroker
public class SocketConfiguration implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints ( StompEndpointRegistry registry ) {
        registry.addEndpoint ( "/socket" ).setAllowedOrigins( String.valueOf ( Collections.singletonList("*") ) ).withSockJS ();
    }

    @Override
    public void configureMessageBroker ( org.springframework.messaging.simp.config.MessageBrokerRegistry registry ) {
        registry.setApplicationDestinationPrefixes ( "/app" );
        registry.enableSimpleBroker ( "/topic" );

    }
}
