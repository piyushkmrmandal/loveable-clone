package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.auth.AuthResponse;
import com.learning.project.loveable_clone.dto.auth.LoginRequest;
import com.learning.project.loveable_clone.dto.auth.SignupRequest;
import com.learning.project.loveable_clone.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
