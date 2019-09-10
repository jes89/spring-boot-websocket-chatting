package com.test.samples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketHandlerRegistry implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(
			org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry registry) {
		registry.addHandler(new WebSocketHandler(), "/socket").setAllowedOrigins("*");
		
	}
	
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
	    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
	    container.setMaxBinaryMessageBufferSize(1024000);
	    return container;
	}
}
