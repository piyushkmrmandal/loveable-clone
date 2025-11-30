package com.learning.project.loveable_clone.dto.subscription;

public record UsageTodayResponse(
        int tokensUsed,
        int tokensList,
        int previewsRunning,
        int previewsLimit
) {
}
