package com.learning.project.loveable_clone.service;

import com.learning.project.loveable_clone.dto.subscription.PlanLimitsResponse;
import com.learning.project.loveable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
