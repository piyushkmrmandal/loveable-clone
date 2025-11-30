package com.learning.project.loveable_clone.dto.member;

import com.learning.project.loveable_clone.enums.ProjectRole;

public record UpdateMemberRoleRequest(
        ProjectRole role
) {
}
