package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.auth.ProfileResponse;
import com.learning.project.loveable_clone.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ProfileResponse getProfile(Long userId) {
        return null;
    }
}
