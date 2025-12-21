package com.learning.project.loveable_clone.dto.member;

import com.learning.project.loveable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role
) {
}
