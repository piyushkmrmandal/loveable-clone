package com.learning.project.loveable_clone.service;

import com.learning.project.loveable_clone.dto.auth.ProfileResponse;
import org.jspecify.annotations.Nullable;

public interface UserService {

    ProfileResponse getProfile(Long userId);
}
