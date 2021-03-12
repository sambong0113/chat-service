package com.example.chatservice.handlers;

import com.example.chatservice.APIResponse;
import com.example.chatservice.User;
import com.example.chatservice.authentication.JWTUtil;
import com.example.chatservice.authentication.PBKDF2Encoder;
import com.example.chatservice.repository.UserRepository;
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
    private UserRepository userRepository;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request
                .bodyToMono(User.class)
                .flatMap(user -> userRepository.findByUsername(user.getUsername()).flatMap(userDetails -> {
                    if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(new APIResponse(true, "", jwtUtil.generateToken(userDetails))));
                    }
                    return Mono.empty();
                })).switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(new APIResponse(false, "login failure"))));
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {
        return request
                .bodyToMono(User.class)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    System.out.println(user.getUsername());
                    System.out.println(user.getPassword());
                    System.out.println(user.getNickname());
                    return user;
                })
                .flatMap(user -> userRepository.findByUsername(user.getUsername())
                    .flatMap(dbUser -> ServerResponse.badRequest().body(BodyInserters.fromValue(new APIResponse(false, "user already exist"))))
                .switchIfEmpty(userRepository
                    .save(user)
                    .flatMap(savedUser -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(savedUser)))));
    }
}
