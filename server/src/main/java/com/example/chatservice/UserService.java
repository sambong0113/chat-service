package com.example.chatservice;

import com.example.chatservice.authentication.Role;
import com.example.chatservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private Map<String, User> data;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void test() {
        userRepository.findAll().flatMap(user -> {
            System.out.println("USER");
            System.out.println(user);
            return null;
        }).subscribe();
    }

    @PostConstruct
    public void init() {
        data = new HashMap<>();

        data.put("user", new User("user", "J8BVW1DB52kIuZWOfRmSW3kYGvjgR9IwSYnPOGL9EWg=", Arrays.asList(Role.ROLE_USER), "eddy", true));
    }

    public Mono<User> findByUsername(String username) {
        test();
        if (data.containsKey(username)) {
            return Mono.just(data.get(username));
        } else {
            return Mono.empty();
        }
    }
}
