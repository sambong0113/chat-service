package com.example.chatservice.routes;

import com.example.chatservice.handlers.AuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AuthRouter {
    @Bean
    public RouterFunction<ServerResponse> authRoute(AuthHandler authHandler) {
        return RouterFunctions
                .route(
                        RequestPredicates.POST("/api/signup").and(accept(MediaType.APPLICATION_JSON)),
                        authHandler::signUp
                ).andRoute(
                        RequestPredicates.POST("/api/login").and(accept(MediaType.APPLICATION_JSON)),
                        authHandler::login
                );
    }
}
