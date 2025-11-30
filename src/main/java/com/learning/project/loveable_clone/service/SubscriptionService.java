package com.learning.project.loveable_clone.service;

import com.learning.project.loveable_clone.dto.subscription.CheckoutRequest;
import com.learning.project.loveable_clone.dto.subscription.CheckoutResponse;
import com.learning.project.loveable_clone.dto.subscription.PortalResponse;
import com.learning.project.loveable_clone.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
