package com.learning.project.loveable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.learning.project.loveable_clone.enums.ProjectPermissions.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(EDIT, VIEW_MEMBERS),
    VIEWER(Set.of(VIEW, VIEW_MEMBERS)),
    OWNER(Set.of(VIEW, EDIT, DELETE, MANAGE_MEMBERS, VIEW_MEMBERS));

    ProjectRole(ProjectPermissions... permissions) {
        this.permissions = Set.of(permissions);
    }

    private final Set<ProjectPermissions> permissions;
}
