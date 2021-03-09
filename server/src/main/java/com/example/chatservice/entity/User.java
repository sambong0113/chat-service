package com.example.chatservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table("User")
@Data @AllArgsConstructor @RequiredArgsConstructor
public class User {
    private String username;
    private String password;
    private String nickname;

}
