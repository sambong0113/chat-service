package com.example.chatservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class APIResponse {
    private Boolean success;
    private String description;
    private String token;

    public APIResponse(Boolean success, String description) {
        this.success = success;
        this.description = description;
    }
}
