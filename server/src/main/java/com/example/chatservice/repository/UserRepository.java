package com.example.chatservice.repository;

import com.example.chatservice.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    @Query("Select * FROM User WHERE username = $1")
    Flux<User> findByUsername(String username);
}
