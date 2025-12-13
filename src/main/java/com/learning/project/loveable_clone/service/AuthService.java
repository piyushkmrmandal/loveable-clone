package com.learning.project.loveable_clone.service;

import com.learning.project.loveable_clone.dto.auth.AuthResponse;
import com.learning.project.loveable_clone.dto.auth.LoginRequest;
import com.learning.project.loveable_clone.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
