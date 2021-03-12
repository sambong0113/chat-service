package com.example.chatservice.repository;

import com.example.chatservice.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    @Query("Select * FROM User WHERE username = :username")
    Mono<User> findByUsername(String username);

    @Query("Insert Into User Values(\':user.username\', \':user.password', \':user.nickname\')")
    Mono<User> createUser(User user);
}
