package com.example.chatservice;

import com.example.chatservice.authentication.Role;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, User> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();

        data.put("user", new User("user", "J8BVW1DB52kIuZWOfRmSW3kYGvjgR9IwSYnPOGL9EWg=", Arrays.asList(Role.ROLE_USER), "eddy", true));
    }

    public Mono<User> findByUsername(String username) {
        if (data.containsKey(username)) {
            return Mono.just(data.get(username));
        } else {
            return Mono.empty();
        }
    }
}
