package com.learning.project.loveable_clone.dto.subscription;

public record PlanResponse(
       Long id,
       String name,
       Integer maxProjects,
       Integer maxTokensPerDay,
       Boolean unlimitedAi,
       String price
) {
}
