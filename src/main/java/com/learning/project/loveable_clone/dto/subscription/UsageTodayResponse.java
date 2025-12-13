package com.learning.project.loveable_clone.dto.subscription;

public record UsageTodayResponse(
        Integer tokensUsed,
        Integer tokensList,
        Integer previewsRunning,
        Integer previewsLimit
) {
}
