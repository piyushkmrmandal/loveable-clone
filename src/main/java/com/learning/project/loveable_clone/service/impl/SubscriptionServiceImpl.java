package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.subscription.CheckoutRequest;
import com.learning.project.loveable_clone.dto.subscription.CheckoutResponse;
import com.learning.project.loveable_clone.dto.subscription.PortalResponse;
import com.learning.project.loveable_clone.dto.subscription.SubscriptionResponse;
import com.learning.project.loveable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
