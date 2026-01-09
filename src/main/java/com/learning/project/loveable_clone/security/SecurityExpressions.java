package com.learning.project.loveable_clone.security;

import com.learning.project.loveable_clone.enums.ProjectPermissions;
import com.learning.project.loveable_clone.repository.ProjectMemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static com.learning.project.loveable_clone.enums.ProjectPermissions.*;

@Component("security")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityExpressions {

    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    private boolean hasPermission(Long projectId, ProjectPermissions projectPermissions){
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
//                .map(role -> role.equals(OWNER) || role.equals(EDITOR) || role.equals(VIEWER))   - with roles
                .map(role -> role.getPermissions().contains(projectPermissions))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId){
        return hasPermission(projectId, VIEW);
    }

    public boolean canEditProject(Long projectId){
        return hasPermission(projectId, EDIT);
    }

    public boolean canDeleteProject(Long projectId){
        return hasPermission(projectId, DELETE);
    }

    public boolean canViewMembers(Long projectId){
        return hasPermission(projectId, VIEW_MEMBERS);
    }

    public boolean canManageMembers(Long projectId){
        return hasPermission(projectId, MANAGE_MEMBERS);
    }


}
