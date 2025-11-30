package com.learning.project.loveable_clone.dto.auth;

public record AuthResponse(String token,
                           UserProfileResponse userProfileResponse) {
}
