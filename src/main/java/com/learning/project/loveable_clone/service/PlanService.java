package com.learning.project.loveable_clone.service;

import com.learning.project.loveable_clone.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
