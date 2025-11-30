package com.learning.project.loveable_clone.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
