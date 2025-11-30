package com.learning.project.loveable_clone.dto.auth;

public record ProfileResponse(
        Long id,
        String email,
        String name,
        String avatarUrl
) {
}
