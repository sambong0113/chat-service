package com.example.chatservice.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private Boolean success;
    private String token;
}
