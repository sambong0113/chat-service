package com.example.chatservice.handlers;

import com.example.chatservice.User;
import com.example.chatservice.UserService;
import com.example.chatservice.authentication.AuthResponse;
import com.example.chatservice.authentication.JWTUtil;
import com.example.chatservice.authentication.PBKDF2Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
public class AuthHandler {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request
                .bodyToMono(User.class)
                .flatMap(user -> userService.findByUsername(user.getUsername()).flatMap(userDetails -> {
                    if (passwordEncoder.encode(user.getPassword()).equals(userDetails.getPassword())) {
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(new AuthResponse(true, jwtUtil.generateToken(userDetails))));
                    }
                    return Mono.empty();
                })).switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(new AuthResponse(false, null))));
    }
}
