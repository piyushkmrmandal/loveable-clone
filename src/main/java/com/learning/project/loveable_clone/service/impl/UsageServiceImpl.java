package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.subscription.PlanLimitsResponse;
import com.learning.project.loveable_clone.dto.subscription.UsageTodayResponse;
import com.learning.project.loveable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
